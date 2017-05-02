package com.gzncloud.domain;

public class Account {
    private Long user;

    private String wechat_merchant;

    private String wechat_appid;

    private String wechat_appsecret;

    private String wechat_apisecret;

    private String wechat_certificate;

    private String wechat_personalAccount;

    private String wechat_personalName;

    private Short wechat_rate;

    private String alipay_merchant;

    private String alipay_subaccount;

    private String alipay_personalAccount;

    private String alipay_personalName;

    private Short alipay_rate;

    private String hotline;

    private String qrcode;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getWechat_merchant() {
        return wechat_merchant;
    }

    public void setWechat_merchant(String wechat_merchant) {
        this.wechat_merchant = wechat_merchant == null ? null : wechat_merchant.trim();
    }

    public String getWechat_appid() {
        return wechat_appid;
    }

    public void setWechat_appid(String wechat_appid) {
        this.wechat_appid = wechat_appid == null ? null : wechat_appid.trim();
    }

    public String getWechat_appsecret() {
        return wechat_appsecret;
    }

    public void setWechat_appsecret(String wechat_appsecret) {
        this.wechat_appsecret = wechat_appsecret == null ? null : wechat_appsecret.trim();
    }

    public String getWechat_apisecret() {
        return wechat_apisecret;
    }

    public void setWechat_apisecret(String wechat_apisecret) {
        this.wechat_apisecret = wechat_apisecret == null ? null : wechat_apisecret.trim();
    }

    public String getWechat_certificate() {
        return wechat_certificate;
    }

    public void setWechat_certificate(String wechat_certificate) {
        this.wechat_certificate = wechat_certificate == null ? null : wechat_certificate.trim();
    }

    public String getWechat_personalAccount() {
        return wechat_personalAccount;
    }

    public void setWechat_personalAccount(String wechat_personalAccount) {
        this.wechat_personalAccount = wechat_personalAccount == null ? null : wechat_personalAccount.trim();
    }

    public String getWechat_personalName() {
        return wechat_personalName;
    }

    public void setWechat_personalName(String wechat_personalName) {
        this.wechat_personalName = wechat_personalName == null ? null : wechat_personalName.trim();
    }

    public Short getWechat_rate() {
        return wechat_rate;
    }

    public void setWechat_rate(Short wechat_rate) {
        this.wechat_rate = wechat_rate;
    }

    public String getAlipay_merchant() {
        return alipay_merchant;
    }

    public void setAlipay_merchant(String alipay_merchant) {
        this.alipay_merchant = alipay_merchant == null ? null : alipay_merchant.trim();
    }

    public String getAlipay_subaccount() {
        return alipay_subaccount;
    }

    public void setAlipay_subaccount(String alipay_subaccount) {
        this.alipay_subaccount = alipay_subaccount == null ? null : alipay_subaccount.trim();
    }

    public String getAlipay_personalAccount() {
        return alipay_personalAccount;
    }

    public void setAlipay_personalAccount(String alipay_personalAccount) {
        this.alipay_personalAccount = alipay_personalAccount == null ? null : alipay_personalAccount.trim();
    }

    public String getAlipay_personalName() {
        return alipay_personalName;
    }

    public void setAlipay_personalName(String alipay_personalName) {
        this.alipay_personalName = alipay_personalName == null ? null : alipay_personalName.trim();
    }

    public Short getAlipay_rate() {
        return alipay_rate;
    }

    public void setAlipay_rate(Short alipay_rate) {
        this.alipay_rate = alipay_rate;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline == null ? null : hotline.trim();
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }
}