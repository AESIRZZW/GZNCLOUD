package com.gzncloud.domain;

import java.util.Date;

public class LockerLogs {
    private Long id;

    private Long locker;

    private Long cabinet;

    private Long device;

    private Long order;

    private Date timestamp;

    private Long good;

    private Long amount;

    private String currency;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLocker() {
        return locker;
    }

    public void setLocker(Long locker) {
        this.locker = locker;
    }

    public Long getCabinet() {
        return cabinet;
    }

    public void setCabinet(Long cabinet) {
        this.cabinet = cabinet;
    }

    public Long getDevice() {
        return device;
    }

    public void setDevice(Long device) {
        this.device = device;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getGood() {
        return good;
    }

    public void setGood(Long good) {
        this.good = good;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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