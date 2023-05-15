package com.wx.dto.notify;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("支付订单通知接口参数模型(加密)")
public class NotifyPayEncryptDto {
    @ApiModelProperty("通知ID")
    @JSONField(name = "id")
    private String id;
    @ApiModelProperty("通知创建时间")
    @JSONField(name = "create_time")
    private String createTime;
    @ApiModelProperty("通知数据类型")
    @JSONField(name = "resource_type")
    private String resourceType;
    @ApiModelProperty("时间类型")
    @JSONField(name = "event_type")
    private String eventType;
    @ApiModelProperty("回调摘要")
    @JSONField(name = "summary")
    private String summary;
    @ApiModelProperty("通知类型")
    @JSONField(name = "resource")
    private Resource resource;

    @Data
    public static class Resource{
        @ApiModelProperty("原始类型")
        @JSONField(name = "original_type")
        private String original_type;
        @ApiModelProperty("加密算法类型")
        @JSONField(name = "algorithm")
        private String algorithm;
        @ApiModelProperty("数据密文")
        @JSONField(name = "ciphertext")
        private String ciphertext;
        @ApiModelProperty("附加数据")
        @JSONField(name = "associated_data")
        private String associated_data;
        @ApiModelProperty("随机串")
        @JSONField(name = "nonce")
        private String nonce;
    }
}
