package me.zji.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 我的测试类
 * Created by imyu on 2017/2/23.
 */
public class MyTest {
    @Test
    public void testHashMapGet() {
        Map map = new HashMap();
        map.put("xx","true");
        System.out.println("true".equals(map.get("xx")));
    }

    @Test
    public void tolower() {
        System.out.println("DAYINIT\n".toLowerCase() +
                "RECEIVEMARKET\n".toLowerCase() +
                "STARTTUXEDO\n".toLowerCase() +
                "DOWNTUXEDO\n".toLowerCase() +
                "CHECKDATA\n".toLowerCase() +
                "EXPREQUEST\n".toLowerCase() +
                "IMPORTDATA\n".toLowerCase() +
                "DEALDATA\n".toLowerCase() +
                "LIQCARRYOVER\n".toLowerCase());
    }

    @Test
    public void testddd() {
        String sss = "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `vc_tradeacco` varchar(17) NOT NULL COMMENT '交易账号',\n" +
                "  `c_moneytype` varchar(3) NOT NULL COMMENT '币种【105：人民币，344：港币，840美元，978：欧元】',\n" +
                "  `en_balance` decimal(19,2) DEFAULT 0 COMMENT '余额【余额=可用余额+冻结余额】',\n" +
                "  `en_enbalance` decimal(19,2) DEFAULT NULL COMMENT '可用余额',\n" +
                "  `en_imbalance` decimal(19,2) DEFAULT NULL COMMENT '冻结余额',";
        Pattern pattern = Pattern.compile("(?<=`)\\w+(?=`)");
        Matcher matcher = pattern.matcher(sss);
        while (matcher.find()) {
            System.out.print(matcher.group() +", ");
        }
    }

    @Test
    public void print(){
        System.out.println("  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `vc_custno` varchar(18) NOT NULL COMMENT '客户编号',\n" +
                "  `c_custtype` char(1) NOT NULL COMMENT '客户类型【0：机构，1：个人】',\n" +
                "  `vc_custname` varchar(64) NOT NULL COMMENT '客户名称',\n" +
                "  `vc_identityno` varchar(32) DEFAULT NULL COMMENT '证件号码',\n" +
                "  `vc_tacode` varchar(8) NOT NULL COMMENT 'ta编号',\n" +
                "  `vc_tradeacco` varchar(17) DEFAULT NULL COMMENT '交易账号',\n" +
                "  `vc_taacco` varchar(12) DEFAULT NULL COMMENT '基金账号' ,\n" +
                "  `mobile` varchar(15) DEFAULT NULL COMMENT '手机',\n" +
                "  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',\n" +
                "  `address` varchar(200) DEFAULT NULL COMMENT '地址',\n" +
                "  `vc_opendate` varchar(10) DEFAULT NULL COMMENT '增开时间',");
    }

}
