package me.zji.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员系统管理控制器
 * Created by imyu on 2017/2/18.
 */
@Controller
public class AdminUserController {
    /**
     * View 管理员系统管理页面
     * @return
     */
    @RequestMapping(value = "/admin/user/index.html")
    public String user() {
        return "/admin/user/index";
    }

    /**
     * View 管理员系统管理 新增管理员
     * @return
     */
    @RequestMapping(value = "/admin/user/add.html")
    public String add() {
        return "/admin/user/add";
    }

    /**
     * Action 管理员系统管理 新增管理员
     * @return
     */
    @RequestMapping(value = "/admin/user/doadd")
    @ResponseBody
    public Object doAdd(@RequestBody Map param, Model model) {
        System.out.println("ddddd");
        return model;
    }

    /**
     * View 管理员系统管理 管理员列表
     * @return
     */
    @RequestMapping(value = "/admin/user/list.html")
    public String list() {
        return "/admin/user/list";
    }
}
