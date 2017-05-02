package com.gzncloud.wechat.model.pay;


import com.gzncloud.wechat.annotation.Optional;

/**
 * 二维码支付请求对象
 */
public class QrPayRequest extends PayRequest {

    /**
     * 商品ID
     * {@link com.gzncloud.wechat.model.enums.WepayField#PRODUCT_ID}
     */
    @Optional(any = false)
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "QrPayRequest{" +
                "productId='" + productId + '\'' +
                "} " + super.toString();
    }
}
