package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.entity.CustInfo;
import me.zji.entity.ProductInfo;
import me.zji.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
    @Autowired
    DealProcessService dealProcessService;
    @Autowired
    StaticTradeBalanceService staticTradeBalanceService;
    @Autowired
    SystemStaticBalanceService systemStaticBalanceService;

    /**
     * View 业务交易管理首页
     * @return
     */
    @RequestMapping(value = "/admin/trade/index.html")
    public String index() {
        return "/admin/trade/index";
    }

    /**
     * View 开户
     * @return
     */
    @RequestMapping(value = "/admin/trade/accoopen.html")
    public String accoOpen(Model model) {
        Map<String, Object> selectItemMap = new HashMap<String, Object>();
        selectItemMap.put("taCodeSelect", dynamicSelectService.selectTaCode());

        model.addAttribute("selectItemMap", selectItemMap);
        return "/admin/trade/accoopen";
    }

    /**
     * Action 开户
     * @return
     */
    @RequestMapping(value = "/admin/trade/addaccoopen")
    @ResponseBody
    public Object addAccoOpen(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 业务交易逻辑 */
        {
            if (custInfoService.queryByIdentityNo((String) params.get("identityNo")) != null) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "该证件号码已经开过户";
            } else {
                data = custUserService.create(params);
                data.put("requestNo","系统暂不支持");
            }
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        model.put("data", data);
        return model;
    }

    /**
     * View 资金存入
     * @return
     */
    @RequestMapping(value = "/admin/trade/income.html")
    public String income(Model model) {
        Map<String, Object> selectItemMap = new HashMap<String, Object>();
        selectItemMap.put("bankAccoSelect", dynamicSelectService.selectBankAccoInfo());

        model.addAttribute("selectItemMap", selectItemMap);
        return "/admin/trade/income";
    }

    /**
     * Action 资金存入
     * @return
     */
    @RequestMapping(value = "/admin/trade/addincome")
    @ResponseBody
    public Object addIncome(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "存入成功";
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 业务交易逻辑 */
        {
            Map result1 = systemStaticBalanceService.income((String) params.get("bankAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
            if (Integer.valueOf(result1.get("resultCode").toString()) == CommonConstants.RESULT_SUCEESS) {
                Map result2 = staticTradeBalanceService.income((String) params.get("tradeAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
                if(Integer.valueOf(result2.get("resultCode").toString()) == CommonConstants.RESULT_SUCEESS) {
                    data.put("tradeAcco", params.get("tradeAcco"));
                    data.put("bankAcco", params.get("bankAcco"));
                    data.put("requestNo","系统暂不支持");
                } else {
                    systemStaticBalanceService.expend((String) params.get("bankAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
                    resultCode = CommonConstants.RESULT_FAILURE;
                    errorInfo = (String) result2.get("errorInfo");
                }
            } else {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = (String) result1.get("errorInfo");
            }
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        model.put("data", data);
        return model;
    }


    /**
     * View 资金支出
     * @return
     */
    @RequestMapping(value = "/admin/trade/expend.html")
    public String expend(Model model) {
        Map<String, Object> selectItemMap = new HashMap<String, Object>();
        selectItemMap.put("bankAccoSelect", dynamicSelectService.selectBankAccoInfo());

        model.addAttribute("selectItemMap", selectItemMap);
        return "/admin/trade/expend";
    }

    /**
     * Action 资金存入
     * @return
     */
    @RequestMapping(value = "/admin/trade/addexpend")
    @ResponseBody
    public Object addExpend(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "支出成功";
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 业务交易逻辑 */
        {

            Map result1 = staticTradeBalanceService.expend((String) params.get("tradeAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
            if (Integer.valueOf(result1.get("resultCode").toString()) == CommonConstants.RESULT_SUCEESS) {
                Map result2 = systemStaticBalanceService.expend((String) params.get("bankAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
                if (Integer.valueOf(result2.get("resultCode").toString()) == CommonConstants.RESULT_SUCEESS) {
                    data.put("tradeAcco", params.get("tradeAcco"));
                    data.put("bankAcco", params.get("bankAcco"));
                    data.put("requestNo","系统暂不支持");
                } else {
                    staticTradeBalanceService.income((String) params.get("tradeAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
                    resultCode = CommonConstants.RESULT_FAILURE;
                    errorInfo = "系统清算账户余额不足";
                }
            } else {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = (String) result1.get("errorInfo");
            }
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        model.put("data", data);
        return model;
    }



}
