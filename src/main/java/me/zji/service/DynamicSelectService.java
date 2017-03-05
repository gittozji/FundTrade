package me.zji.service;

import me.zji.dto.SelectItem;

import java.util.List;

/**
 * 选择框数据动态获取服务
 * Created by imyu on 2017/2/24.
 */
public interface DynamicSelectService {
    /**
     * 获取网点
     * @return
     */
    List<SelectItem> selectNetStation();

    /**
     * 获取TA
     * @return
     */
    List<SelectItem> selectTaCode();

    /**
     * 获取系统的银行账户账户信息
     * @return
     */
    List<SelectItem> selectBankAccoInfo();
}
