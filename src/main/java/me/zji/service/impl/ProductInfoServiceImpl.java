package me.zji.service.impl;

import me.zji.dao.ProductInfoDao;
import me.zji.entity.ProductInfo;
import me.zji.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产品信息服务
 * Created by imyu on 2017/3/3.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoDao productInfoDao;
    public void create(ProductInfo productInfo) {
        productInfoDao.create(productInfo);
    }

    public ProductInfo queryByProductCode(String productCode) {
        return productInfoDao.queryByProductCode(productCode);
    }
}
