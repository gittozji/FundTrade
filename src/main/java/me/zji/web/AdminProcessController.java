package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.entity.DealProcess;
import me.zji.service.DealProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.krb5.internal.PAData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程控制管理控制器
 * Created by imyu on 2017/2/25.
 */
@Controller
public class AdminProcessController {
    @Autowired
    DealProcessService dealProcessService;
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
    public String edit(Model model) {
        Map<String, DealProcess> dealProcessMap = DealProcess.listToMap(dealProcessService.getCurrentDealProcess());
        model.addAttribute("dealProcessMap", dealProcessMap);
        return "/admin/process/edit";
    }

    /**
     * 流程操作
     * @return
     */
    @RequestMapping(value = "/admin/process/doedit")
    @ResponseBody
    public Object doEdit(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        DealProcess dealProcess = new DealProcess();
        dealProcess.setProcedurCode((String) param.get("procedurCode"));
        if ("dayinit".equals(dealProcess.getProcedurCode())) {
            dealProcessService.dayInit();
        } else {
            dealProcessService.update(dealProcess);
        }
        return model;
    }

    /**
     * 日初始化
     * @return
     */
    @RequestMapping(value = "/admin/process/dodayinit")
    public Object doDayInit() {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * 接收行情
     * @return
     */
    @RequestMapping(value = "/admin/process/receivemarket")
    public Object doReceiveMarket() {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * 导入确认数据
     * @return
     */
    @RequestMapping(value = "/admin/process/importdata")
    public Object doImportData() {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * 处理确认数据
     * @return
     */
    @RequestMapping(value = "/admin/process/dealdata")
    public Object doDealData() {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        return null;
    }

    /**
     * 启动交易
     * @return
     */
    @RequestMapping(value = "/admin/process/starttuxedo")
    public Object doStartTuxedo() {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * 停止柜台交易
     * @return
     */
    @RequestMapping(value = "/admin/process/dowmtuxedo")
    public Object doDownTuxdedo() {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * 交易预处理
     * @return
     */
    @RequestMapping(value = "/admin/process/checkdata")
    public Object doCheckData() {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * 导出申请数据
     * @return
     */
    @RequestMapping(value = "/admin/process/exprequest")
    public Object doExpRequest() {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * 清算结转
     * @return
     */
    @RequestMapping(value = "/admin/process/liqcarryover")
    public Object doLiqcarryOver() {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }
}
