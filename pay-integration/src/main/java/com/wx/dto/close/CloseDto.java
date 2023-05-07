package com.wx.dto.close;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("关闭订单接口参数模型")
@Data
public class CloseDto {

    @ApiModelProperty("直连商户号")
    @JSONField(name = "mchid")
    private Integer mchid;
    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
}
