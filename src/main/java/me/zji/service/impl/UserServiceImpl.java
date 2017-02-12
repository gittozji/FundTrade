package me.zji.service.impl;

import me.zji.dao.UserDao;
import me.zji.entity.User;
import me.zji.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by imyu on 2017/2/11.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    public User getUser(int id) {
        return userDao.getUserById(id);
    }
    public User queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }
}
