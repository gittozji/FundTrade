package me.zji.web;

import me.zji.dao.UserRoleDao;
import me.zji.entity.User;
import me.zji.entity.UserRole;
import me.zji.security.UsernamePasswordUsertypeToken;
import me.zji.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        System.out.println(session.getId());

        System.out.println("ddddd");
        return "home";
    }
    @RequestMapping(value = "/ajax")
    @ResponseBody
    public Object ajax(@RequestBody Map paramter, HttpSession httpSessiion){

        User httpUser  = (User) httpSessiion.getAttribute("user");

        User user = userService.queryByUsername("001");

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        List<UserRole> userRoleList = userRoleDao.queryByExample(userRole);
        Subject subject = SecurityUtils.getSubject();
        boolean b1 = subject.hasRole("custom");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("custom");
        arrayList.add("admin_process");
        arrayList.add("admin_maintain");
        arrayList.add("admin_trade");
        arrayList.add("admin_user");
        boolean[] b2 = subject.hasRoles(arrayList);
        System.out.println(b1);

        System.out.println(httpSessiion.getId());
        System.out.println((subject.getSession()).getId());

        Session session = subject.getSession();
        User user1 = (User) session.getAttribute("user");
        System.out.println(user1);

        System.out.println(user.getPassword());
        System.out.println(paramter.get("hello"));
        Map map = new HashMap();
        map.put("info","好啊");
        return map;
    }
    @RequestMapping(value = "/dologin")
    public Object login(HttpServletRequest request) {
        String errorInfo = null;
        try {
            UsernamePasswordUsertypeToken usernamePasswordUsertypeToken = new UsernamePasswordUsertypeToken(request.getParameter("username"),request.getParameter("password"),0);
            Subject subject = SecurityUtils.getSubject();
            // 先退出之前可能在线的用户
            subject.logout();
            // 用户登录
            subject.login(usernamePasswordUsertypeToken);
            // 用户信息保存到session
            Session session = subject.getSession();
            User user = userService.queryByUsername((String) subject.getPrincipal());
            session.setAttribute("user", user);
        } catch (UnknownAccountException e) {
           errorInfo = "用户名不正确";
        } catch (IncorrectCredentialsException e) {
            errorInfo = "密码不正确";
        } catch (LockedAccountException e) {
            errorInfo = "用户被锁定";
        }
        System.out.println(errorInfo);
        return "home";
    }
}
