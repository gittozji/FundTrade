package me.zji.service.impl;

import me.zji.dao.DealProcessDao;
import me.zji.entity.DealProcess;
import me.zji.service.DealProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程控制服务
 * Created by imyu on 2017/2/26.
 */
@Service
public class DealProcessServiceImpl implements DealProcessService {
    public static List<DealProcess> dealProcessList = null;
    @Autowired
    DealProcessDao dealProcessDao;

    public List<DealProcess> getCurrentDealProcess() {
        if(dealProcessList == null) {
            dealProcessList = queryAll();
        }
        return dealProcessList;
    }

    public List<DealProcess> queryAll() {
        return dealProcessDao.queryAll();
    }

    public void update(DealProcess dealProcess) {
        dealProcess.setState(1); // 设置为处理中
        dealProcessDao.update(dealProcess);
        // TODO:进行处理
        dealProcess.setState(2);
        dealProcessDao.update(dealProcess);

        if ("liqcarryover".equals(dealProcess.getProcedurCode())) {
            DealProcess dealProcess1 = new DealProcess();
            dealProcess.setProcedurCode("dayinit");
            dealProcess.setState(0);
            dealProcessDao.update(dealProcess);
        }

        dealProcessList = queryAll(); // 通知内存中的流程数据
    }

    public void dayInit() {
        dealProcessDao.setInit();
        DealProcess dealProcess = new DealProcess();
        dealProcess.setProcedurCode("dayinit");
        dealProcess.setState(2);
        dealProcessDao.update(dealProcess);

        dealProcessList = queryAll(); // 通知内存中的流程数据
    }
}