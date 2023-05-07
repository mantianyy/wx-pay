package com.wx.dto.create;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("下单接口返回信息")
public class CreateOrderResDto {
    @ApiModelProperty("预支付交易会话标识")
    @JSONField(name = "prepay_id")
    private String prepayId;
}
