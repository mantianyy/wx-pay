package com.wx.dto.wx.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jiang漫天
 */
@Data
@ApiModel(value = "订单支付通知")
public class PayOrderNotifyDto {

    @ApiModelProperty("通知ID")
    @JSONField(name = "id")
    private String id;
    @ApiModelProperty("通知创建时间")
    @JSONField(name = "create_time")
    private String createTime;

    @ApiModelProperty("通知类型")
    @JSONField(name = "event_type")
    private String eventType;

    @ApiModelProperty("appid")
    @JSONField(name = "resource_type")
    private String resourceType;

    @ApiModelProperty("通知数据类型")
    @JSONField(name = "resource")
    private ResourceDto resourceDto;

    @ApiModelProperty("回调摘要")
    @JSONField(name = "summary")
    private String summary;
}
