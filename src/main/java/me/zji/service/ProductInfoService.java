package me.zji.service;

import me.zji.entity.ProductInfo;

/**
 * 产品信息服务
 * Created by imyu on 2017/3/3.
 */
public interface ProductInfoService {
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
}
