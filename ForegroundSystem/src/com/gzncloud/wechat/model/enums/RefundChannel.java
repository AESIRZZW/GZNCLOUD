package com.gzncloud.wechat.model.enums;

/**
 * 退款渠道
 */
public enum RefundChannel {

    /**
     * 原路退款
     */
    ORIGINAL("ORIGINAL"),

    /**
     * 退款到余额
     */
    BALANCE("BALANCE");

    private String type;

    private RefundChannel(String type){
        this.type = type;
    }

    public String type(){
        return type;
    }

    public static RefundChannel from(String t){
        for (RefundChannel rc : RefundChannel.values()){
            if (rc.type().equals(t)){
                return rc;
            }
        }
        throw new IllegalArgumentException("unknown apply channel: " + t);
    }
}
