package me.zji.service.impl;

import me.zji.dao.StaticTradeBalanceDao;
import me.zji.dao.TaCommunicationDao;
import me.zji.entity.*;
import me.zji.service.*;
import me.zji.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * TA 通信服务
 * Created by imyu on 2017/3/9.
 */
@Service
public class TaCommunicationServiceImpl implements TaCommunicationService {
    @Autowired
    TaCommunicationDao taCommunicationDao;
    @Autowired
    DynamicProductInfoService dynamicProductInfoService;
    @Autowired
    StaticTradeBalanceDao staticTradeBalanceDao;
    @Autowired
    SystemStaticBalanceService systemStaticBalanceService;
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    StaticShareService staticShareService;
    @Autowired
    @Qualifier("applicationProperties")
    Properties properties;
    /**
     * 插入并返回插入后的结果
     *
     * @param taCommunication
     * @return
     */
    public TaCommunication create(TaCommunication taCommunication) {
        taCommunicationDao.create(taCommunication);
        return taCommunication;
    }

    /**
     * 导出文件
     *
     * @return
     */
    public boolean taOutput() {
        String path = properties.get("ta.output.path") + "/" + DateUtil.getNowDate() + ".txt"; // 导出文件路径
        File file;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            TaCommunication taCommunicationExample = new TaCommunication();
            taCommunicationExample.setStatus("0"); // 未导出给TA的记录
            List<TaCommunication> taCommunicationList = taCommunicationDao.queryByExample(taCommunicationExample);
            file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);
            for (TaCommunication item: taCommunicationList) {
                pw.println(item.toString());
            }
            pw.flush();
            return true; // 通知上层，成功！
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 通知上层，失败！
        } finally {
            try{
                if (fw != null)
                    fw.flush();
                if (pw != null)
                    pw.close();
                if (fw != null)
                    fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 接收行情文件
     * @return
     */
    public boolean receiveMarket() {
        String path = properties.get("ta.input.path") + "/" + DateUtil.getNowDate() + ".txt"; // 导出文件路径
        Map<String, Object> data = getTaReturnData(new File(path));
        if (data != null) {
            List<TaCommunication> dataList = (List<TaCommunication>) data.get("888");
            if (dataList != null && dataList.size() > 0) { // 有数据进行行情导入
                for (TaCommunication item : dataList) {
                    if ("1".equals(item.getStatus())) {
                        dynamicProductInfoService.createOrUpdate(new DynamicProductInfo(item.getProductCode(), item.getStnav()));
                    }
                }
            }
        }
        return true;
    }

    /**
     * 导入申请数据
     *
     * @return
     */
    public boolean importData() {
        String path = properties.get("ta.input.path") + "/" + DateUtil.getNowDate() + ".txt"; // 导出文件路径
        Map<String, Object> data = getTaReturnData(new File(path));
        if (data != null) {
            return doBuy((List<TaCommunication>) data.get("020")) && doBuy((List<TaCommunication>) data.get("022")) && doAtoneFor((List<TaCommunication>) data.get("024"));
        }
        return false;
    }

    private boolean doBuy(List<TaCommunication> list) {
        if (list != null && list.size() > 0) { // 有数据进行认申购确认
            for (TaCommunication item : list) {
                /**tacommunication 置c_status为成功*/
                TaCommunication taCommunication = new TaCommunication();
                taCommunication.setSerialNo(item.getSerialNo());
                taCommunication.setStatus(item.getStatus());
                taCommunicationDao.updateStatusByserialNo(taCommunication);

                if ("1".equals(item.getStatus())) { // 成功
                    /**statictradebalance 余额减少冻结减少*/
                    StaticTradeBalance staticTradeBalance = new StaticTradeBalance(item.getTradeAcco(), item.getMoneyType());
                    staticTradeBalance = staticTradeBalanceDao.queryByTradeAccoAndMoneyType(staticTradeBalance);
                    staticTradeBalance.setBalance(staticTradeBalance.getBalance() - item.getBalance());
                    staticTradeBalance.setImBalance(staticTradeBalance.getImBalance() - item.getBalance());
                    staticTradeBalanceDao.update(staticTradeBalance);

                    /**systemstaticbalance 系统账户资金减少（即划给TA）*/
                    ProductInfo productInfo = productInfoService.queryByProductCode(item.getProductCode());
                    systemStaticBalanceService.expend(productInfo.getBankAcco(), productInfo.getMoneyType(), item.getBalance());

                    /**更新静态份额*/
                    // TODO: 待测试
                    DynamicProductInfo dynamicProductInfo = dynamicProductInfoService.queryByProductCode(item.getProductCode());
                    StaticShare staticShare = new StaticShare();
                    staticShare.setProductCode(item.getProductCode());
                    staticShare.setTaAcco(item.getTaAcco());
                    staticShare = staticShareService.queryByCodeAndAcco(staticShare);
                    if (staticShare == null) {
                        staticShare = new StaticShare();
                        staticShare.setTotalShare(dynamicProductInfo.getStnav() * item.getBalance());
                        staticShare.setTaAcco(item.getTaAcco());
                        staticShare.setProductCode(item.getProductCode());
                    } else {
                        staticShare.setTotalShare(staticShare.getTotalShare() + dynamicProductInfo.getStnav() * item.getBalance());
                    }
                    staticShareService.createOrUpdate(staticShare);

                } else if ("2".equals(item.getStatus())) { // 失败
                    /**statictradebalance 可用资金增加冻结减少*/
                    StaticTradeBalance staticTradeBalance = new StaticTradeBalance(item.getTradeAcco(), item.getMoneyType());
                    staticTradeBalance = staticTradeBalanceDao.queryByTradeAccoAndMoneyType(staticTradeBalance);
                    staticTradeBalance.setEnBalance(staticTradeBalance.getEnBalance() + item.getBalance());
                    staticTradeBalance.setImBalance(staticTradeBalance.getImBalance() - item.getBalance());
                    staticTradeBalanceDao.update(staticTradeBalance);
                }
            }
        }
        return true;
    }

    private boolean doAtoneFor(List<TaCommunication> list) {

        return true;
    }


    /**
     * 获取TA返回信息
     * @param file
     * @return
     */
    private Map<String, Object> getTaReturnData(File file) {
        Map<String, Object> data = new HashMap<String, Object>();
        List<TaCommunication> offerList = new ArrayList<TaCommunication>();
        List<TaCommunication> applyList = new ArrayList<TaCommunication>();
        List<TaCommunication> atoneforList = new ArrayList<TaCommunication>();
        List<TaCommunication> stnavList = new ArrayList<TaCommunication>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                if (tempString.trim().startsWith("//")) {
                    continue;
                }
                Map<String, String> itemMap = new HashMap<String, String>();
                String[] splitArray = tempString.split(",");
                for (String couple : splitArray) {
                    String[] items = couple.split("=");
                    itemMap.put(items[0].trim(), items[1].trim());
                }
                TaCommunication temp = map2TaCommunication(itemMap);
                if ("020".equals(temp.getBusinFlag())) {
                    offerList.add(temp);
                } else if ("022".equals(temp.getBusinFlag())) {
                    applyList.add(temp);
                } else if ("024".equals(temp.getBusinFlag())) {
                    atoneforList.add(temp);
                } else if ("888".equals(temp.getBusinFlag())) {
                    stnavList.add(temp);
                }
            }
            data.put("020", offerList);
            data.put("022", applyList);
            data.put("024", atoneforList);
            data.put("888", stnavList);
        } catch (IOException e) {
            e.printStackTrace();
            data = null; // 通知上层，失败！
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return data;
    }

    /**
     * Map 转 TaCommunication
     * @param data
     * @return
     */
    private TaCommunication map2TaCommunication(Map<String, String> data) {
        TaCommunication taCommunication = new TaCommunication();
        taCommunication.setTaCode(getStringRealValue(data.get("taCode")));
        taCommunication.setTaAcco(getStringRealValue(data.get("taAcco")));
        taCommunication.setProductCode(getStringRealValue(data.get("productCode")));
        taCommunication.setBusinFlag(getStringRealValue(data.get("businFlag")));
        taCommunication.setStatus(getStringRealValue(data.get("status")));
        taCommunication.setSerialNo(getStringRealValue(data.get("serialNo")));
        taCommunication.setOccurDate(getStringRealValue(data.get("occurDate")));
        taCommunication.setTradeAcco(getStringRealValue(data.get("tradeAcco")));
        taCommunication.setMoneyType(getStringRealValue(data.get("moneyType")));
        if (getStringRealValue(data.get("balance")) != null && data.get("balance") != "") {
            taCommunication.setBalance(Double.valueOf(data.get("balance")));
        }
        if (getStringRealValue(data.get("share")) != null && data.get("share") != "" && !"null".equals(data.get("share"))) {
            taCommunication.setShare(Double.valueOf(data.get("share")));
        }
        if (getStringRealValue(data.get("stnav")) != null && data.get("stnav") != "") {
            taCommunication.setStnav(Double.valueOf(data.get("stnav")));
        }
        return taCommunication;
    }

    /**
     * “null” -> null
     * @return
     */
    private String getStringRealValue(String src) {
        if ("null".equals(src) || "NULL".equals(src)) {
            return null;
        }
        return src;
    }

}
