package me.zji.web;

import me.zji.dao.UserDao;
import me.zji.entity.TestUser;
import me.zji.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by imyu on 2017/1/20.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/home")
    public String home(User user){
        return "home";
    }
    @RequestMapping(value = "/ajax")
    @ResponseBody
    public Object ajax(@RequestBody Map paramter){


        System.out.println(paramter.get("hello"));
        Map map = new HashMap();
        map.put("info","好啊");
        return map;
    }
}
