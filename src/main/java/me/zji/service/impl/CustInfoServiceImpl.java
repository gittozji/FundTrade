package me.zji.service.impl;

import me.zji.dao.CustInfoDao;
import me.zji.entity.CustInfo;
import me.zji.service.CustInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户信息服务
 * Created by imyu on 2017/3/4.
 */
@Service
public class CustInfoServiceImpl implements CustInfoService {
    @Autowired
    CustInfoDao custInfoDao;
    public void create(CustInfo custInfo) {
        custInfoDao.create(custInfo);
        System.out.println(custInfo.getCustNo());
    }
}
