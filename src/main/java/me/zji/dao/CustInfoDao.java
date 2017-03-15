package me.zji.dao;

import me.zji.entity.CustInfo;

import java.util.Map;

/**
 * 客户信息 DAO
 * Created by imyu on 2017/3/4.
 */
public interface CustInfoDao {
    void create(Map params);

    void update(CustInfo custInfo);

    CustInfo queryByIdentityNo(String identityNo);

    CustInfo queryByCustNo(String custNo);

    CustInfo queryByUsername(String username);
}
