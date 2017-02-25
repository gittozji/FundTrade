package me.zji.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 流程控制管理控制器
 * Created by imyu on 2017/2/25.
 */
@Controller
public class AdminProcessController {
    /**
     * View 流程控制管理首页
     * @return
     */
    @RequestMapping(value = "/admin/process/index.html")
    public String index() {
        return "/admin/process/index";
    }

    /**
     * View 流程控制操作页面
     * @return
     */
    @RequestMapping(value = "/admin/process/edit.html")
    public String edit() {
        return "/admin/process/edit";
    }
}
