package me.zji.service;

import me.zji.entity.CustInfo;

/**
 * 客户信息服务
 * Created by imyu on 2017/3/4.
 */
public interface CustInfoService {
    void create(CustInfo custInfo);
    CustInfo queryByIdentityNo(String identityNo);
}
