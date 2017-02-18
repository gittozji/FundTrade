package me.zji.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员系统管理控制器
 * Created by imyu on 2017/2/18.
 */
@Controller
public class AdminUserWidget {
    /**
     * View 管理员系统管理页面
     * @return
     */
    @RequestMapping(value = "/admin/user/index.html")
    public String user() {
        return "/admin/user/index";
    }
}
