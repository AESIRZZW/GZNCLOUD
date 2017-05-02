package com.gzncloud.domain;

import java.util.Date;

public class Orders {
    private Long id;

    private String trade_no;

    private Date created_at;

    private Date payed_at;

    private Boolean succeeded;

    private String customer1;

    private String customer2;

    private Long device;

    private Long user;

    private String payment_channel;

    private Long initial_amount;

    private Short rate;

    private Long discounted_amount;

    private String currency;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no == null ? null : trade_no.trim();
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getPayed_at() {
        return payed_at;
    }

    public void setPayed_at(Date payed_at) {
        this.payed_at = payed_at;
    }

    public Boolean getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
    }

    public String getCustomer1() {
        return customer1;
    }

    public void setCustomer1(String customer1) {
        this.customer1 = customer1 == null ? null : customer1.trim();
    }

    public String getCustomer2() {
        return customer2;
    }

    public void setCustomer2(String customer2) {
        this.customer2 = customer2 == null ? null : customer2.trim();
    }

    public Long getDevice() {
        return device;
    }

    public void setDevice(Long device) {
        this.device = device;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getPayment_channel() {
        return payment_channel;
    }

    public void setPayment_channel(String payment_channel) {
        this.payment_channel = payment_channel == null ? null : payment_channel.trim();
    }

    public Long getInitial_amount() {
        return initial_amount;
    }

    public void setInitial_amount(Long initial_amount) {
        this.initial_amount = initial_amount;
    }

    public Short getRate() {
        return rate;
    }

    public void setRate(Short rate) {
        this.rate = rate;
    }

    public Long getDiscounted_amount() {
        return discounted_amount;
    }

    public void setDiscounted_amount(Long discounted_amount) {
        this.discounted_amount = discounted_amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}