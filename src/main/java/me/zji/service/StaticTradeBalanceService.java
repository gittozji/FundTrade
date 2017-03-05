package me.zji.service;

import me.zji.entity.StaticTradeBalance;

import java.util.Map;

/**
 * 交易账号静态资金服务
 * Created by imyu on 2017/3/5.
 */
public interface StaticTradeBalanceService {
    Map income(String tradeAcco, String moneyType, Double count);
    Map expend(String tradeAcco, String moneyType, Double count);
}
