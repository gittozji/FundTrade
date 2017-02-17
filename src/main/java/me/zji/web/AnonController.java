package me.zji.web;

import me.zji.entity.User;
import me.zji.security.UsernamePasswordUsertypeToken;
import me.zji.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 非全资页面控制器
 * Created by imyu on 2017/2/17.
 */
@Controller
public class AnonController {
    @Autowired
    UserService userService;
    /**
     * View 登录
     * @return
     */
    @RequestMapping(value = "/login.html")
    public String login(HttpServletRequest httpServletRequest, Model model){
        String errorInfo = (String) httpServletRequest.getAttribute("errorInfo");
        System.out.println(errorInfo);
//        model.addAttribute("errorInfo", "惺惺惜惺惺");
        return "anon/login";
    }

    /**
     * View 注册
     * @return
     */
    @RequestMapping(value = "/sign.html")
    public String sign(){
        return "anon/sign";
    }

    /**
     * Action 登录
     * @return
     */
    @RequestMapping(value = "/anon/dologin", method=RequestMethod.POST)
    public String doLogin(HttpServletRequest httpServletRequest, ModelMap model, @ModelAttribute(value="user") User u) {

        String viewName = null;
        String errorInfo = null;
        try {
            String s = (String) httpServletRequest.getParameter("username");
            UsernamePasswordUsertypeToken usernamePasswordUsertypeToken = new UsernamePasswordUsertypeToken((String) httpServletRequest.getAttribute("username"),(String) httpServletRequest.getAttribute("password"),0);
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
        if(errorInfo != null || true) {
            model.addAttribute("errorInfo", "54545");
            viewName = "redirect:/login.html";
        } else {
            viewName = "redirect:/sign.html";
        }
        return viewName;
    }

    /**
     * Action 注册
     * @return
     */
    @RequestMapping(value = "/anon/dosign")
    public Object dosign() {
        return null;
    }
}
