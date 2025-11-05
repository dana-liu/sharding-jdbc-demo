package com.lww.sharding.user.entity;

import java.util.Date;

public class StudentBoughtClass {
    private Long id;

    private Long userNumber;

    private Long courseNumber;

    private Long buyClassNumber;

    private Long classNumber;

    private Long preMiniClassNumber;

    private Long miniClassNumber;

    private Byte status;

    private Byte type;

    private Byte source;

    private Integer productType;

    private Byte isRenewals;

    private Long orderNumber;

    private Date renewalDate;

    private Date createTime;

    private Date updateTime;

    private String wechatName;

    private Boolean isgift;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
    }

    public Long getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Long courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Long getBuyClassNumber() {
        return buyClassNumber;
    }

    public void setBuyClassNumber(Long buyClassNumber) {
        this.buyClassNumber = buyClassNumber;
    }

    public Long getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Long classNumber) {
        this.classNumber = classNumber;
    }

    public Long getPreMiniClassNumber() {
        return preMiniClassNumber;
    }

    public void setPreMiniClassNumber(Long preMiniClassNumber) {
        this.preMiniClassNumber = preMiniClassNumber;
    }

    public Long getMiniClassNumber() {
        return miniClassNumber;
    }

    public void setMiniClassNumber(Long miniClassNumber) {
        this.miniClassNumber = miniClassNumber;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Byte getIsRenewals() {
        return isRenewals;
    }

    public void setIsRenewals(Byte isRenewals) {
        this.isRenewals = isRenewals;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName == null ? null : wechatName.trim();
    }

    public Boolean getIsgift() {
        return isgift;
    }

    public void setIsgift(Boolean isgift) {
        this.isgift = isgift;
    }
}