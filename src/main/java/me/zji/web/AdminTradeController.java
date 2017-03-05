package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.entity.CustInfo;
import me.zji.entity.ProductInfo;
import me.zji.service.CustInfoService;
import me.zji.service.CustUserService;
import me.zji.service.DynamicSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务交易控制器
 * Created by imyu on 2017/3/4.
 */
@Controller
public class AdminTradeController {

    @Autowired
    DynamicSelectService dynamicSelectService;
    @Autowired
    CustInfoService custInfoService;
    @Autowired
    CustUserService custUserService;

    /**
     * View 流程控制管理首页
     * @return
     */
    @RequestMapping(value = "/admin/trade/index.html")
    public String index() {
        return "/admin/trade/index";
    }

    /**
     * View 产品信息设置
     * @return
     */
    @RequestMapping(value = "/admin/trade/accoopen.html")
    public String productInfo(Model model) {

        Map<String, Object> selectItemMap = new HashMap<String, Object>();
        selectItemMap.put("taCodeSelect", dynamicSelectService.selectTaCode());
        selectItemMap.put("bankAccoSelect", dynamicSelectService.selectBankAccoInfo());

        model.addAttribute("selectItemMap", selectItemMap);

        CustInfo custInfo = new CustInfo();
        custInfo.setCustType("0");
        custInfo.setCustName("00");
        custInfo.setIdentityNo("00");
        custInfo.setTaCode("0008");
        Map params = new HashMap();
        params.put("custType","0");
        params.put("custName","0");
        params.put("identityNo","0");
        params.put("taCode","0008");
        params.put("bankName","000");
        params.put("bankAcco","0000");
        custUserService.create(params);
//        Map<String, Object> selectItemMap = new HashMap<String, Object>();
//        selectItemMap.put("taCodeSelect", dynamicSelectService.selectTaCode());
//        selectItemMap.put("bankAccoSelect", dynamicSelectService.selectBankAccoInfo());
//
//        model.addAttribute("selectItemMap", selectItemMap);
        return "/admin/trade/accoopen";
    }

    /**
     * Action 添加产品信息
     * @return
     */
    @RequestMapping(value = "/admin/trade/addaccoopen")
    @ResponseBody
    public Object addProductInfo(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;

//        ProductInfo productInfo = new ProductInfo();



//        if(productInfoService.queryByProductCode(productInfo.getProductCode()) != null) {
//            resultCode = CommonConstants.RESULT_FAILURE;
//            errorInfo = "已经存在该产品代码";
//        } else {
//            productInfoService.create(productInfo);
//        }

        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }



}
