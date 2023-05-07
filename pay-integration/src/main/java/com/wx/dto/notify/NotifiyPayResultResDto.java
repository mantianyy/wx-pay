package com.wx.dto.notify;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("支付订单通知返回参数模型")
public class NotifiyPayResultResDto {

    @ApiModelProperty("返回状态码")
    @JSONField(name = "code")
    private String code;
    @ApiModelProperty("返回信息")
    @JSONField(name = "message")
    private String message;
}
