package com.wx.pay.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PayerDto {

    @ApiModelProperty("用户标识")
    @JSONField(name = "openid")
    private String openId;
}
