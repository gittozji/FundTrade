package me.zji.dao;

import me.zji.entity.User;
import org.mybatis.spring.annotation.MapperScan;

/**
 * Created by imyu on 2017/2/11.
 */

public interface UserDao {
    User getUserById(int id);
    User queryByUsername(String username);
}
