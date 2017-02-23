package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.dto.UserAdmin;
import me.zji.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员系统管理控制器
 * Created by imyu on 2017/2/18.
 */
@Controller
public class AdminUserController {
    @Autowired
    UserAdminService userAdminService;
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
    public Object doAdd(@RequestBody Map param) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        UserAdmin userAdmin = userAdminService.queryByUsername((String) param.get("username"));
        if(userAdmin == null) {
            userAdminService.createByMap(param);
        } else {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "用户名已存在";
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
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
