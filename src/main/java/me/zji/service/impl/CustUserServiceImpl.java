package me.zji.service.impl;

import me.zji.dao.CustUserDao;
import me.zji.security.PasswordUtils;
import me.zji.service.CustUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户用户关键信息服务
 * Created by imyu on 2017/3/5.
 */
@Service
public class CustUserServiceImpl implements CustUserService {
    @Autowired
    CustUserDao custUserDao;
    public Map create(Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        params.put("password", PasswordUtils.encryptPassword((String) params.get("password")));
        custUserDao.create(params);
        Map map = new HashMap();
        map.put("custNo", params.get("custNo"));
        map.put("taAcco", params.get("taAcco"));
        map.put("tradeAcco", params.get("tradeAcco"));
        return map;
    }
}
