package me.zji.dao;

import me.zji.entity.CustInfo;

/**
 * 客户信息 DAO
 * Created by imyu on 2017/3/4.
 */
public interface CustInfoDao {
    void create(CustInfo custInfo);

    void update(CustInfo custInfo);

    CustInfo queryByIdentityNo(String identityNo);
}
