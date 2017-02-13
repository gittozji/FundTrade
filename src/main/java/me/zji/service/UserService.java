package me.zji.service;

import me.zji.entity.User;

/**
 * 用户服务
 * Created by imyu on 2017/2/11.
 */
public interface UserService {
    User getUser(int id);
    User queryByUsername(String username);
}
