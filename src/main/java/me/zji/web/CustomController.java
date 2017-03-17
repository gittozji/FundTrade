package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.dao.TradeAccoDao;
import me.zji.dto.AdminUser;
import me.zji.dto.CustUser;
import me.zji.entity.CustInfo;
import me.zji.entity.TradeAcco;
import me.zji.entity.User;
import me.zji.security.UsernamePasswordUsertypeToken;
import me.zji.service.CustInfoService;
import me.zji.service.DynamicSelectService;
import me.zji.service.PasswordService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户页面控制器（7*24小时渠道）
 * Created by imyu on 2017/3/15.
 */
@Controller
public class CustomController {
    @Autowired
    CustInfoService custInfoService;
    @Autowired
    DynamicSelectService dynamicSelectService;
    @Autowired
    PasswordService passwordService;
    @Autowired
    TradeAccoDao tradeAccoDao;

    /**
     * View 管理员系统管理页面
     * @return
     */
    @RequestMapping(value = "/custom/index.html")
    public String user(@ModelAttribute("errorInfo") String errorInfo, Model model) {
        model.addAttribute("errorInfo", errorInfo);
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        CustUser custUser = (CustUser) session.getAttribute("user");

        CustInfo custInfo = custInfoService.queryByUsername(custUser.getUsername());
        model.addAttribute("custInfo", custInfo);
        if (custInfo == null) {
            return "/custom/index";
        }

        /**下拉框*/
        Map<String, Object> selectItemMap = new HashMap<String, Object>();
        selectItemMap.put("taCodeSelect", dynamicSelectService.selectTaCode());
        selectItemMap.put("taAccoSelect", dynamicSelectService.selectTaAccoByCustNo(custInfo.getCustNo()));
        selectItemMap.put("tradeAccoSelect", dynamicSelectService.selectTradeAccoByCustNo(custInfo.getCustNo()));
        model.addAttribute("selectItemMap", selectItemMap);




        return "/custom/index";
    }

    /**
     * Action 绑定客户编号
     * @return
     */
    @RequestMapping(value = "/custom/dobind")
    public String doLogin(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        String viewName = "redirect:/custom/index.html";
        String errorInfo = null;

        String custNo = httpServletRequest.getParameter("custNo");
        String tradeAcco = httpServletRequest.getParameter("tradeAcco");
        String password = httpServletRequest.getParameter("password");

        /**判断交易账号是否对应正确的客户编号*/
        TradeAcco temp = tradeAccoDao.queryByTradeAcco(tradeAcco);
        if (temp == null || !custNo.equals(temp.getCustNo())) {
            errorInfo = "客户编号或交易账号不正确";
        }

        /**判断密码*/
        Map map = passwordService.verificateTradeAccoPassword(tradeAcco,password);
        if (Integer.valueOf(map.get("resultCode").toString()) == CommonConstants.RESULT_FAILURE) {
            errorInfo = (String) map.get("errorInfo");
        }

        if(errorInfo != null) {
            redirectAttributes.addFlashAttribute("errorInfo",errorInfo);
        } else {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            CustUser custUser = (CustUser) session.getAttribute("user");
            CustInfo custInfo = custInfoService.queryByCustNo(custNo);
            custInfo.setUserName(custUser.getUsername());
            custInfoService.update(custInfo);
        }
        return viewName;
    }
}
