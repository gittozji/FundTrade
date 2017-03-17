package me.zji.service.impl;

import me.zji.dao.DynamicSelectDao;
import me.zji.dao.TaAccoDao;
import me.zji.dao.TradeAccoDao;
import me.zji.dto.SelectItem;
import me.zji.entity.TaAcco;
import me.zji.entity.TradeAcco;
import me.zji.service.DynamicSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择框数据动态获取服务
 * Created by imyu on 2017/2/24.
 */
@Service
public class DynamicSelectServiceImpl implements DynamicSelectService {
    @Autowired
    DynamicSelectDao dynamicSelectDao;
    @Autowired
    TaAccoDao taAccoDao;
    @Autowired
    TradeAccoDao tradeAccoDao;

    public List<SelectItem> selectNetStation() {
        return dynamicSelectDao.selectNetStation();
    }

    public List<SelectItem> selectTaCode() {
        return dynamicSelectDao.selectTaCode();
    }

    public List<SelectItem> selectBankAccoInfo() {
        return dynamicSelectDao.selectBankAccoInfo();
    }

    public List<SelectItem> selectTaAccoByCustNo(String custNo) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<TaAcco> taAccoList = taAccoDao.queryByCustNo(custNo);
        for (TaAcco taAcco : taAccoList) {
            SelectItem selectItem = new SelectItem();
            selectItem.setItem(taAcco.getTaAcco());
            selectItem.setCaption(taAcco.getTaAcco());
            selectItems.add(selectItem);
        }
        return selectItems;
    }

    /**
     * 通过客户编号获取交易账号
     *
     * @param custNo
     * @return
     */
    public List<SelectItem> selectTradeAccoByCustNo(String custNo) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<TradeAcco> tradeAccoList = tradeAccoDao.queryByCustNo(custNo);
        for (TradeAcco tradeAcco : tradeAccoList) {
            SelectItem selectItem = new SelectItem();
            selectItem.setItem(tradeAcco.getTradeAcco());
            selectItem.setCaption(tradeAcco.getTradeAcco());
            selectItems.add(selectItem);
        }
        return selectItems;
    }
}
