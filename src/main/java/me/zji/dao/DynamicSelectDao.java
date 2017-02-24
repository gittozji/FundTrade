package me.zji.dao;

import me.zji.dto.SelectItem;

import java.util.List;

/**
 * 选择框数据动态获取 Dao
 * Created by imyu on 2017/2/24.
 */
public interface DynamicSelectDao {
    /**
     * 获取网点
     * @return
     */
    List<SelectItem> selectNetStation();
}
