package me.zji.service;

import me.zji.dao.CustUserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 客户用户关键信息服务
 * Created by imyu on 2017/3/5.
 */
public interface CustUserService {
    /**
     * 插入并返回客户编号、TA账号和交易账号
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    Map create(Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
