package me.zji.web;

import me.zji.dto.UserAdmin;
import me.zji.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 管理员控制器
 * Created by imyu on 2017/2/18.
 */
@Controller
public class AdminController {
    /**
     * View 管理员首页
     * @return
     */
    @RequestMapping(value = "/admin/index.html")
    public String admin(){
        return "/admin/index";
    }
}
