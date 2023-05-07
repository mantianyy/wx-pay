package com.wx.dto.refund;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("退款结果通知返回模型")
public class RefundResultNotificationResDto {
    @ApiModelProperty("返回状态码")
    @JSONField(name = "code")
    private String code;
    @ApiModelProperty("返回信息")
    @JSONField(name = "message")
    private String message;
}
