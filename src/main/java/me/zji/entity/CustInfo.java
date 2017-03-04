package me.zji.entity;

/**
 * 客户信息
 * Created by imyu on 2017/3/4.
 */
public class CustInfo extends Id {
    String custNo;
    String custType;
    String custName;
    String identityNo;
    String taCode;
    String tradeAcco;
    String taAcco;
    String mobile;
    String email;
    String address;
    String openDate;

    public CustInfo(String custNo, String custType, String custName, String identityNo, String taCode, String tradeAcco, String taAcco, String mobile, String email, String address, String openDate) {
        this.custNo = custNo;
        this.custType = custType;
        this.custName = custName;
        this.identityNo = identityNo;
        this.taCode = taCode;
        this.tradeAcco = tradeAcco;
        this.taAcco = taAcco;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.openDate = openDate;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getTaCode() {
        return taCode;
    }

    public void setTaCode(String taCode) {
        this.taCode = taCode;
    }

    public String getTradeAcco() {
        return tradeAcco;
    }

    public void setTradeAcco(String tradeAcco) {
        this.tradeAcco = tradeAcco;
    }

    public String getTaAcco() {
        return taAcco;
    }

    public void setTaAcco(String taAcco) {
        this.taAcco = taAcco;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
}
