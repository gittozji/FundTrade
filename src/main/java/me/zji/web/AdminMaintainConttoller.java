package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.entity.Day;
import me.zji.entity.NetStation;
import me.zji.entity.ProductInfo;
import me.zji.entity.Ta;
import me.zji.service.DayService;
import me.zji.service.NetStationService;
import me.zji.service.ProductInfoService;
import me.zji.service.TaService;
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
    @Autowired
    TaService taService;
    @Autowired
    NetStationService netStationService;
    @Autowired
    ProductInfoService productInfoService;
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

    /**
     * View 产品信息设置
     * @return
     */
    @RequestMapping(value = "/admin/maintain/productinfo.html")
    public String productInfo() {
        return "/admin/maintain/productinfo";
    }

    /**
     * Action 添加产品信息
     * @return
     */
    @RequestMapping(value = "/admin/maintain/addproductinfo")
    @ResponseBody
    public Object addProductInfo(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;

        ProductInfo productInfo = new ProductInfo();
        productInfo.setTaCode((String) param.get("taCode"));
        productInfo.setBankAcco((String) param.get("bankAcco"));
        productInfo.setChargeType((String) param.get("chargeType"));
        productInfo.setDividendMethod((String) param.get("dividendMethod"));
        productInfo.setInvestDirection((String) param.get("investDirection"));
        productInfo.setInvestRegion((String) param.get("investRegion"));
        productInfo.setIssueStartDate((String) param.get("issueStartDate"));
        productInfo.setManageRatio(Float.valueOf(param.get("manageRatio").toString()));
        productInfo.setMoneyType((String) param.get("moneyType"));
        productInfo.setProductOperation((String) param.get("productOperation"));
        productInfo.setProductStatus((String) param.get("productStatus"));
        productInfo.setProductRiskLevel((String) param.get("productRiskLevel"));
        productInfo.setProductCategory((String) param.get("productCategory"));
        productInfo.setProductCode((String) param.get("productCode"));
        productInfo.setProductName((String) param.get("productName"));


        if(productInfoService.queryByProductCode(productInfo.getProductCode()) != null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "已经存在该产品代码";
        } else {
            productInfoService.create(productInfo);
        }

        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * View TA信息
     * @return
     */
    @RequestMapping(value = "/admin/maintain/ta.html")
    public String ta() {
        return "/admin/maintain/ta";
    }

    /**
     * Action 添加TA信息
     * @return
     */
    @RequestMapping(value = "/admin/maintain/addta")
    @ResponseBody
    public Object addTa(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;

        Ta ta = new Ta();
        ta.setTaCode((String) param.get("code"));
        ta.setTaName((String) param.get("name"));
        if(taService.queryByCode(ta.getTaCode()) != null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "已经存在该TA代码";
        } else {
            taService.create(ta);
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * View 网点设置
     * @return
     */
    @RequestMapping(value = "/admin/maintain/netstation.html")
    public String netStation() {
        return "/admin/maintain/netstation";
    }

    /**
     * Action 添加网点信息
     * @return
     */
    @RequestMapping(value = "/admin/maintain/addnetstation")
    @ResponseBody
    public Object addNetStation(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;

        NetStation netStation = new NetStation();
        netStation.setNetNo((String) param.get("netNo"));
        netStation.setNetName((String) param.get("netName"));
        netStation.setAddress((String) param.get("address"));
        if(netStationService.queryByNetNo(netStation.getNetNo()) != null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "已经存在该网点编号";
        } else {
            netStationService.create(netStation);
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }




}
