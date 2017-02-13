package me.zji.web;

import me.zji.dao.UserRoleDao;
import me.zji.entity.User;
import me.zji.entity.UserRole;
import me.zji.security.UsernamePasswordUsertypeToken;
import me.zji.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @RequiresRoles(value = {"custom","admin_process"}, logical = Logical.OR)
    public String home(User user){

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String s = (String) session.getAttribute("123");


        System.out.println("ddddd");
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
    @RequestMapping(value = "/dologin")
    public Object login(HttpServletRequest request) {
        System.out.println("begin");
        try{
            UsernamePasswordUsertypeToken usernamePasswordUsertypeToken = new UsernamePasswordUsertypeToken(request.getParameter("username"),request.getParameter("password"),0);
            SecurityUtils.getSubject().login(usernamePasswordUsertypeToken);
            Subject subject = SecurityUtils.getSubject();
            boolean boo0 = subject.hasRole("custom");
            boolean boo1 = subject.hasRole("admin_process");

            Session session = subject.getSession();
            session.setAttribute("123",subject.getPrincipal());
            System.out.println(session.toString());


        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return "home";
    }
}
