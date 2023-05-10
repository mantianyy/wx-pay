package com.wx.dto.wx.dto.query_refund;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("优惠券信息模型")
public class PromotionDetail {
    @ApiModelProperty("券ID")
    @JSONField(name = "promotion_id")
    private String promotionId;
    @ApiModelProperty("优惠范围")
    @JSONField(name = "scope")
    private String scope;
    @ApiModelProperty("优惠类型")
    @JSONField(name = "type")
    private String type;
    @ApiModelProperty("优惠券面额")
    @JSONField(name = "amount")
    private String amount;
    @ApiModelProperty("优惠退款金额")
    @JSONField(name = "refund_amount")
    private String refundAmount;
    @ApiModelProperty("商品列表")
    @JSONField(name = "goods_detail")
    private List<GoodsDetail> goodsDetailList;

}
