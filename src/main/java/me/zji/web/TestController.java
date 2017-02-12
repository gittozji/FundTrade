package me.zji.web;

import me.zji.dao.UserRoleDao;
import me.zji.entity.User;
import me.zji.entity.UserRole;
import me.zji.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by imyu on 2017/1/20.
 */
@Controller
public class TestController {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleDao userRoleDao;

    @RequestMapping(value = "/home")
    public String home(User user){
        return "home";
    }
    @RequestMapping(value = "/ajax")
    @ResponseBody
    public Object ajax(@RequestBody Map paramter){
        User user = userService.queryByUsername("001");

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        List<UserRole> userRoleList = userRoleDao.queryByExample(userRole);

        System.out.println(user.getPassword());
        System.out.println(paramter.get("hello"));
        Map map = new HashMap();
        map.put("info","好啊");
        return map;
    }
}
