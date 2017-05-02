package com.gzncloud.alipay.model.refund;

import java.io.Serializable;
import java.util.List;

import com.gzncloud.alipay.annotation.Optional;

/**
 * 退款明细
 */
public class RefundDetail implements Serializable {

    private static final long serialVersionUID = -145560925778001071L;

    /**
     * 服务器异步通知页面路径
     * {@link com.gzncloud.alipay.model.enums.AlipayField#NOTIFY_URL}
     */
    @Optional
    private String notifyUrl;


    /**
     * 退款批次号
     * {@link com.gzncloud.alipay.model.enums.AlipayField#BATCH_NO}
     */
    private String batchNo;

    /**
     * 单笔数据集
     * {@link com.gzncloud.alipay.model.enums.AlipayField#DETAIL_DATA}
     */
    private List<RefundDetailData> detailDatas;

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public List<RefundDetailData> getDetailDatas() {
        return detailDatas;
    }

    public void setDetailDatas(List<RefundDetailData> detailDatas) {
        this.detailDatas = detailDatas;
    }

    /**
     * 格式化退款数据
     * {@link com.gzncloud.alipay.model.enums.AlipayField#DETAIL_DATA}
     * @return 退款数据
     */
    public String formatDetailDatas(){
        StringBuilder details = new StringBuilder();
        for (RefundDetailData data : detailDatas){
            details.append(data.format()).append("#");
        }
        details.deleteCharAt(details.length() - 1);
        return details.toString();
    }

    @Override
    public String toString() {
        return "RefundDetail{" +
                "notifyUrl='" + notifyUrl + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", detailDatas=" + detailDatas +
                '}';
    }
}
