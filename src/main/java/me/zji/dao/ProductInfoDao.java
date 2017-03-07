package me.zji.dao;

import me.zji.entity.ProductInfo;

import java.util.List;

/**
 * 产品信息 DAO
 * Created by imyu on 2017/3/3.
 */
public interface ProductInfoDao {
    /**
     * 创建一条记录
     * @param productInfo
     */
    void create(ProductInfo productInfo);

    /**
     * 通过产品代码查询
     * @param productCode
     * @return
     */
    ProductInfo queryByProductCode(String productCode);

    /**
     * 通过产品状态查询
     * @param productStatu
     * @return
     */
    List<ProductInfo> queryByProductStatus(String productStatu);
}
