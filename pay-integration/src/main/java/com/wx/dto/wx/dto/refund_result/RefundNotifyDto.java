package com.wx.dto.wx.dto.refund_result;

import com.alibaba.fastjson.annotation.JSONField;
import com.wx.dto.wx.dto.ResourceDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("退款结果")
@Data
public class RefundNotifyDto {
    @ApiModelProperty("通知ID")
    @JSONField(name = "id")
    private String id;
    @ApiModelProperty("通知创建时间")
    @JSONField(name = "create_time")
    private String create_time;
    @ApiModelProperty("通知类型")
    @JSONField(name = "event_type")
    private String event_type;
    @ApiModelProperty("通知简要说明")
    @JSONField(name = "summary")
    private String summary;
    @ApiModelProperty("通知数据类型")
    @JSONField(name = "resource_type")
    private String resource_type;
    @ApiModelProperty("通知数据")
    @JSONField(name = "resource")
    private ResourceDto resource;


}
