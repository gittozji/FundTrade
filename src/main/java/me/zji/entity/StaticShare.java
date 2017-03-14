package me.zji.entity;

/**
 * 静态份额
 * Created by imyu on 2017/3/14.
 */
public class StaticShare extends Id {
    String productCode;
    String taAcco;
    Double totalShare;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTaAcco() {
        return taAcco;
    }

    public void setTaAcco(String taAcco) {
        this.taAcco = taAcco;
    }

    public Double getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(Double totalShare) {
        this.totalShare = totalShare;
    }
}
