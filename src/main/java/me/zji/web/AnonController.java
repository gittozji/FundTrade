package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.dto.UserAdmin;
import me.zji.entity.User;
import me.zji.security.UsernamePasswordUsertypeToken;
import me.zji.service.UserAdminService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    UserAdminService userAdminService;
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
    @RequestMapping(value = "/anon/dologin", method=RequestMethod.POST)
    public String doLogin(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        String viewName = null;
        String errorInfo = null;
        User user = null;
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

            user = userService.queryByUsername((String) subject.getPrincipal());
            if (Integer.valueOf(1).equals(user.getType())) { // 管理员
                UserAdmin userAdmin = userAdminService.queryByUsername((String) subject.getPrincipal());
                session.setAttribute("user", userAdmin);
            } else { // 客户
                session.setAttribute("user", user);
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
        } else {
            if("8".equals(user.getType())){ //客户登录
                viewName = "redirect:/sign.html";
            } else { //管理员登录
                viewName = "redirect:/admin/index.html";
            }
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
