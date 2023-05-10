package com.wx.dto.query;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("查询订单接口参数模型")
@Data
public class PayOrderQueryDto {

    @ApiModelProperty("直连商户号")
    @JSONField(name = "mchid")
    private String mchId;
    @ApiModelProperty("商户订单号")
    @JSONField(name = "transaction_id")
    private String transactionId;
}
