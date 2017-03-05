package me.zji.dao;

import me.zji.entity.UserInfo;

/**
 * 客户信息 DAO
 * Created by imyu on 2017/3/5.
 */
public interface UserInfoDao {
    void create();
    UserInfo queryByUserName(String userName);

}
