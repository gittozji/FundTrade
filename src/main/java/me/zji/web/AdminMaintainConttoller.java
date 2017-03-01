package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.entity.Day;
import me.zji.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务维护控制器
 * Created by imyu on 2017/3/1.
 */
@Controller
public class AdminMaintainConttoller {
    @Autowired
    DayService dayService;
    /**
     * View 流程控制管理首页
     * @return
     */
    @RequestMapping(value = "/admin/maintain/index.html")
    public String index() {
        return "/admin/maintain/index";
    }

    /**
     * View 工作日设置
     * @return
     */
    @RequestMapping(value = "/admin/maintain/day.html")
    public String day() {
        return "/admin/maintain/day";
    }

    /**
     * Action 添加工作日
     * @return
     */
    @RequestMapping(value = "/admin/maintain/addday")
    @ResponseBody
    public Object addDay(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;

        Day day = new Day();
        day.setDay((String) param.get("day"));
        String workFlag = param.get("workFlag").toString();
        if("true".equals(workFlag)) {
            day.setWorkFlag(0);
        } else {
            day.setWorkFlag(1);
        }
        dayService.createOrUpdate(day);
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }


}
