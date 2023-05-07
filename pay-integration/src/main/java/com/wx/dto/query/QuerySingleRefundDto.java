package com.wx.dto.query;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询单笔退款订单接口参数模型")
public class QuerySingleRefundDto {

    @ApiModelProperty("商户退款单号")
    @JSONField(name = "out_refund_no")
   private String outRefundNo;
}
