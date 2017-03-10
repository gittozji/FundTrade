package me.zji.entity;

/**
 * TA通信
 * Created by imyu on 2017/3/9.
 */
public class TaCommunication extends Id {
    String taCode;
    String taAcco;
    String productCode;
    String businFlag;
    String status;
    String serialNo;
    String occurDate;
    String tradeAcco;
    String moneyType;
    Double balance;
    Double share;

    public TaCommunication(){}

    public TaCommunication(String taCode, String taAcco, String productCode, String businFlag, String status, String serialNo, String occurDate, String tradeAcco, String moneyType, Double balance, Double share) {
        this.taCode = taCode;
        this.taAcco = taAcco;
        this.productCode = productCode;
        this.businFlag = businFlag;
        this.status = status;
        this.serialNo = serialNo;
        this.occurDate = occurDate;
        this.tradeAcco = tradeAcco;
        this.moneyType = moneyType;
        this.balance = balance;
        this.share = share;
    }

    public String getTaCode() {
        return taCode;
    }

    public void setTaCode(String taCode) {
        this.taCode = taCode;
    }

    public String getTaAcco() {
        return taAcco;
    }

    public void setTaAcco(String taAcco) {
        this.taAcco = taAcco;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBusinFlag() {
        return businFlag;
    }

    public void setBusinFlag(String businFlag) {
        this.businFlag = businFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(String occurDate) {
        this.occurDate = occurDate;
    }

    public String getTradeAcco() {
        return tradeAcco;
    }

    public void setTradeAcco(String tradeAcco) {
        this.tradeAcco = tradeAcco;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }

    @Override
    public String toString() {
        return "taCode=" + taCode + ",taAcco=" + taAcco + ",productCode=" +
                productCode + ",businFlag=" + businFlag + status +
                ",serialNo=" + serialNo + ",occurDate=" + occurDate + ",tradeAcco=" +
                tradeAcco + ",moneyType=" + moneyType + ",balance=" + balance +
                ",share=" + share + ",status=" + status;
    }
}
