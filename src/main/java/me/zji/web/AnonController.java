package me.zji.web;

import me.zji.dto.AdminUser;
import me.zji.entity.User;
import me.zji.security.PasswordUtils;
import me.zji.security.UsernamePasswordUsertypeToken;
import me.zji.service.AdminUserService;
import me.zji.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 非全资页面控制器
 * Created by imyu on 2017/2/17.
 */
@Controller
public class AnonController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserService adminUserService;
    /**
     * View 登录
     * @return
     */
    @RequestMapping(value = "/login.html")
    public String login(@ModelAttribute("errorInfo") String errorInfo, Model model){
        System.out.println(errorInfo);
        model.addAttribute("errorInfo", errorInfo);
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
    @RequestMapping(value = "/anon/dologin")
    public String doLogin(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        String viewName = null;
        String errorInfo = null;
        try {
            UsernamePasswordUsertypeToken usernamePasswordUsertypeToken = new UsernamePasswordUsertypeToken((String) httpServletRequest.getParameter("username"),(String) httpServletRequest.getParameter("password"),0);
            usernamePasswordUsertypeToken.setRememberMe(false);
            Subject subject = SecurityUtils.getSubject();
            // 先退出之前可能在线的用户
            subject.logout();
            // 用户登录
            subject.login(usernamePasswordUsertypeToken);
            // 用户信息保存到session
            Session session = subject.getSession();

            User user = userService.queryByUsername((String) subject.getPrincipal());
            if (Integer.valueOf(1).equals(user.getType())) { // 管理员
                AdminUser adminUser = adminUserService.queryByUsername((String) subject.getPrincipal());
                session.setAttribute("user", adminUser);
                viewName = "redirect:/admin/index.html";
            } else { // 客户
                session.setAttribute("user", user);
                viewName = "redirect:/login.html";
            }

        } catch (UnknownAccountException e) {
            errorInfo = "用户名不正确";
        } catch (IncorrectCredentialsException e) {
            errorInfo = "密码不正确";
        } catch (LockedAccountException e) {
            errorInfo = "用户被锁定";
        }
        if(errorInfo != null) {
            redirectAttributes.addFlashAttribute("errorInfo",errorInfo);
            viewName = "redirect:/login.html";
        }
        return viewName;
    }

    /**
     * Action 退出用户
     * @return
     */
    @RequestMapping(value = "/anon/dologout")
    public String doLogin() {
        String viewName = null;
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        viewName = "redirect:/login.html";
        return viewName;
    }

    /**
     * Action 注册
     * @return
     */
    @RequestMapping(value = "/anon/dosign")
    public Object dosign(User user, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String viewName = null;
        String errorInfo = null;
        if (userService.queryByUsername(user.getUsername()) != null) {
            errorInfo = "用户名已被注册";
        } else {
            user.setNikename("客户");
            user.setType(0);
            user.setPassword(PasswordUtils.encryptPassword(user.getPassword()));
            userService.create(user);
            viewName = "redirect:/login.html";
        }
        if(errorInfo != null) {
            redirectAttributes.addFlashAttribute("errorInfo",errorInfo);
            viewName = "redirect:/sign.html";
        }
        return viewName;
    }
}
