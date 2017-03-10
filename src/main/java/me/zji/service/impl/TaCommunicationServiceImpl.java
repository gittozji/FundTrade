package me.zji.service.impl;

import me.zji.dao.TaCommunicationDao;
import me.zji.entity.TaCommunication;
import me.zji.service.TaCommunicationService;
import me.zji.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

/**
 * TA 通信服务
 * Created by imyu on 2017/3/9.
 */
@Service
public class TaCommunicationServiceImpl implements TaCommunicationService {
    @Autowired
    TaCommunicationDao taCommunicationDao;
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
}
