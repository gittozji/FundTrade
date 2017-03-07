package me.zji.service;

import java.util.Map;

/**
 * 认申购服务
 * Created by imyu on 2017/3/6.
 */
public interface BuyService {
    /**
     * 认购界面查询返填
     * @param tradeAcco
     * @return
     */
    Map queryDataByTradeAccoForOffer(String tradeAcco);

    /**
     * 申购界面查询返填
     * @param tradeAcco
     * @return
     */
    Map queryDataByTradeAccoForApply(String tradeAcco);

    /**
     * 认购
     * @param param
     * @return
     */
    Map offerToBuy(Map param);

    /**
     * 申购购
     * @param param
     * @return
     */
    Map applyToBuy(Map param);
}