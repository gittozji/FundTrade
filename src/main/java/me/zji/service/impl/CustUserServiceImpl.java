package me.zji.service.impl;

import me.zji.dao.CustUserDao;
import me.zji.service.CustUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 客户用户关键信息服务
 * Created by imyu on 2017/3/5.
 */
@Service
public class CustUserServiceImpl implements CustUserService {
    @Autowired
    CustUserDao custUserDao;
    public void create(Map params) {
        custUserDao.create(params);
        System.out.println(params.get("custNo"));
    }
}
