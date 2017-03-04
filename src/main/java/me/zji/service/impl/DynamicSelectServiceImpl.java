package me.zji.service.impl;

import me.zji.dao.DynamicSelectDao;
import me.zji.dto.SelectItem;
import me.zji.service.DynamicSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 选择框数据动态获取服务
 * Created by imyu on 2017/2/24.
 */
@Service
public class DynamicSelectServiceImpl implements DynamicSelectService {
    @Autowired
    DynamicSelectDao dynamicSelectDao;

    public List<SelectItem> selectNetStation() {
        return dynamicSelectDao.selectNetStation();
    }

    public List<SelectItem> selectTaCode() {
        return dynamicSelectDao.selectTaCode();
    }

    public List<SelectItem> selectBankAccoInfo() {
        return dynamicSelectDao.selectBankAccoInfo();
    }
}
