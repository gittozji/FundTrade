package me.zji.service;

import me.zji.dao.CustUserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 客户用户关键信息服务
 * Created by imyu on 2017/3/5.
 */
public interface CustUserService {
    void create(Map params);
}
