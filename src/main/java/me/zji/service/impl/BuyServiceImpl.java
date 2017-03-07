package me.zji.service.impl;

import me.zji.constants.CommonConstants;
import me.zji.dao.CustInfoDao;
import me.zji.dao.ProductInfoDao;
import me.zji.dao.TaAccoDao;
import me.zji.dao.TradeAccoDao;
import me.zji.entity.*;
import me.zji.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 认申购服务
 * Created by imyu on 2017/3/6.
 */
@Service
public class BuyServiceImpl implements BuyService {
    @Autowired
    ProductInfoDao productInfoDao;
    @Autowired
    StaticTradeBalanceService staticTradeBalanceService;
    @Autowired
    SystemStaticBalanceService systemStaticBalanceService;
    @Autowired
    CustInfoDao custInfoDao;
    @Autowired
    TradeAccoDao tradeAccoDao;
    @Autowired
    TaAccoDao taAccoDao;

    public Map queryDataByTradeAccoForOffer(String tradeAcco) {
        Map result = new HashMap();
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "查询成功";
        /** 查询个人信息 */
        TradeAcco tradeAccoEntity = tradeAccoDao.queryByTradeAcco(tradeAcco);
        CustInfo custInfo = custInfoDao.queryByCustNo(tradeAccoEntity.getCustNo());
        List<TaAcco> taAccoList = taAccoDao.queryByCustNo(custInfo.getCustNo());
        /** 查询产品信息 */
        List<ProductInfo> productInfoList = productInfoDao.queryByProductStatus("0"); // 认购

        result.put("tradeAcco", tradeAccoEntity);
        result.put("custInfo", custInfo);
        result.put("taAccoList", taAccoList);
        result.put("productInfoList", productInfoList);
        result.put("resultCode", resultCode);
        result.put("errorInfo", errorInfo);
        return result;
    }

    public Map queryDataByTradeAccoForApply(String tradeAcco) {
        Map result = new HashMap();
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "查询成功";
        /** 查询个人信息 */
        TradeAcco tradeAccoEntity = tradeAccoDao.queryByTradeAcco(tradeAcco);
        CustInfo custInfo = custInfoDao.queryByCustNo(tradeAccoEntity.getCustNo());
        List<TaAcco> taAccoList = taAccoDao.queryByCustNo(custInfo.getCustNo());
        /** 查询产品信息 */
        List<ProductInfo> productInfoList = productInfoDao.queryByProductStatus("1"); // 申购

        result.put("tradeAcco", tradeAccoEntity);
        result.put("custInfo", custInfo);
        result.put("taAccoList", taAccoList);
        result.put("productInfoList", productInfoList);
        result.put("resultCode", resultCode);
        result.put("errorInfo", errorInfo);
        return result;
    }

    public Map offerToBuy(Map param) {
        Map result = new HashMap();
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "认购成功";

        String tradeAcco = (String) param.get("tradeAcco");
        String taAcco = (String) param.get("taAcco");
        String productCode = (String) param.get("productCode");
        Double money = Double.valueOf(param.get("money").toString());
        TaAcco taAccoEntity;
        ProductInfo productInfoEntity;
        // 校验基金账号是否可以购买该产品（是否同一TA）
        {
            taAccoEntity = taAccoDao.queryByTaAcco(taAcco);
            productInfoEntity = productInfoDao.queryByProductCode(productCode);
            if (!taAccoEntity.getTaCode().equals(productInfoEntity.getTaCode())) {
                result.put("resultCode", CommonConstants.RESULT_FAILURE);
                result.put("errorInfo", "该基金账号不能购买本产品");
                return result;
            }
        }
        // 进行划款
        {
            Map result1 = staticTradeBalanceService.expendEI(tradeAcco, productInfoEntity.getMoneyType(), money);
            if (Integer.valueOf(result1.get("resultCode").toString()) == CommonConstants.RESULT_SUCEESS) {
                // 资金划款成功
            } else {
                result.put("resultCode", CommonConstants.RESULT_FAILURE);
                result.put("errorInfo", result1.get("errorInfo"));
                return result;
            }
        }
        result.put("productName", productInfoEntity.getProductName());
        result.put("money", money);
        result.put("taAcco", taAcco);
        result.put("tradeAcco", tradeAcco);
        result.put("requestNo", "系统暂不支持");
        result.put("resultCode", resultCode);
        result.put("errorInfo", errorInfo);
        return result;
    }

    /**
     * 申购购
     *
     * @param param
     * @return
     */
    public Map applyToBuy(Map param) {
        Map result = new HashMap();
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "申购成功";

        String tradeAcco = (String) param.get("tradeAcco");
        String taAcco = (String) param.get("taAcco");
        String productCode = (String) param.get("productCode");
        Double money = Double.valueOf(param.get("money").toString());
        TaAcco taAccoEntity;
        ProductInfo productInfoEntity;
        // 校验基金账号是否可以购买该产品（是否同一TA）
        {
            taAccoEntity = taAccoDao.queryByTaAcco(taAcco);
            productInfoEntity = productInfoDao.queryByProductCode(productCode);
            if (!taAccoEntity.getTaCode().equals(productInfoEntity.getTaCode())) {
                result.put("resultCode", CommonConstants.RESULT_FAILURE);
                result.put("errorInfo", "该基金账号不能购买本产品");
                return result;
            }
        }
        // 进行划款
        {
            Map result1 = staticTradeBalanceService.expendEI(tradeAcco, productInfoEntity.getMoneyType(), money);
            if (Integer.valueOf(result1.get("resultCode").toString()) == CommonConstants.RESULT_SUCEESS) {
                // 资金划款成功
            } else {
                result.put("resultCode", CommonConstants.RESULT_FAILURE);
                result.put("errorInfo", result1.get("errorInfo"));
                return result;
            }
        }
        result.put("productName", productInfoEntity.getProductName());
        result.put("money", money);
        result.put("taAcco", taAcco);
        result.put("tradeAcco", tradeAcco);
        result.put("requestNo", "系统暂不支持");
        result.put("resultCode", resultCode);
        result.put("errorInfo", errorInfo);
        return result;
    }
}
