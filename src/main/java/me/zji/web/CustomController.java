package me.zji.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 客户页面控制器（7*24小时渠道）
 * Created by imyu on 2017/3/15.
 */
@Controller
public class CustomController {
    /**
     * View 管理员系统管理页面
     * @return
     */
    @RequestMapping(value = "/custom/index.html")
    public String user() {
        return "/custom/index";
    }
}
