package com.wx.dto.query;

import com.alibaba.fastjson.annotation.JSONField;
import com.ijpay.wxpay.model.v3.GoodsDetail;
import com.wx.dto.apply.ApplyRefundDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("查询订单接口参数模型")
public class QueryDto {

    @Data
    static class Amount {
        @JSONField(name = "total")
        private Integer total;
        @JSONField(name = "currency")
        private String currency;
    }

    @Data
    static class Payer {
        @ApiModelProperty("支付者")
        @JSONField(name = "openid")
        private String openId;
    }

    @Data
    static class PromotionDetail {
        @Data
        static class GoodsDetail {
            @ApiModelProperty("商品编码")
            @JSONField(name = "goods_id")
            private String goodsId;
            @ApiModelProperty("商品数量")
            @JSONField(name = "quantity")
            private String quantity;
            @ApiModelProperty("商品单价")
            @JSONField(name = "unit_price")
            private String unitPrice;
            @ApiModelProperty("商品优惠金额")
            @JSONField(name = "discount_amount")
            private String discountAmount;
            @ApiModelProperty("商品备注")
            @JSONField(name = "goods_remark")
            private String goodsRemark;
        }

        @ApiModelProperty("券ID")
        @JSONField(name = "appid")
        private String appId;
        @ApiModelProperty("优惠名称")
        @JSONField(name = "name")
        private String name;
        @ApiModelProperty("优惠范围")
        @JSONField(name = "scope")
        private String scope;
        @ApiModelProperty("优惠类型")
        @JSONField(name = "type")
        private String type;
        @ApiModelProperty("优惠券面额")
        @JSONField(name = "amount")
        private String amount;
        @ApiModelProperty("活动ID")
        @JSONField(name = "stock_id")
        private String stockId;
        @ApiModelProperty("微信出资")
        @JSONField(name = "wechatpay_contribute")
        private String wechatpayContribute;
        @ApiModelProperty("商户出资")
        @JSONField(name = "merchant_contribute")
        private String merchantContribute;
        @ApiModelProperty("其他出资")
        @JSONField(name = "other_contribute")
        private String otherContribute;
        @ApiModelProperty("优惠币种")
        @JSONField(name = "currency")
        private String currency;
        @ApiModelProperty("单品列表")
        @JSONField(name = "goods_detail")
        private List<GoodsDetail> goodsDetails;

    }

    @Data
    static class SceneInfo {
        @Data
        static class StoreInfo {
            @ApiModelProperty("门店编号")
            @JSONField(name = "id")
            private String id;
            @ApiModelProperty("门店名称")
            @JSONField(name = "name")
            private String name;
            @ApiModelProperty("地区编码")
            @JSONField(name = "area_code")
            private String area_code;
            @ApiModelProperty("详细地址")
            @JSONField(name = "address")
            private String address;
        }

        @ApiModelProperty("用户终端IP")
        @JSONField(name = "payer_client_ip")
        private String payerClientIp;
        @ApiModelProperty("商户端设备号")
        @JSONField(name = "device_id")
        private String deviceId;
        @ApiModelProperty("商户门店信息")
        @JSONField(name = "store_info")
        private StoreInfo storeInfo;
    }

    @Data
    static class SettleInfo {
        @ApiModelProperty("是否指定分账")
        @JSONField(name = "profit_sharing")
        private Boolean profitSharing;
    }

    @ApiModelProperty("应用ID")
    @JSONField(name = "appid")
    private String appId;
    @ApiModelProperty("直连商户号")
    @JSONField(name = "mchid")
    private String mchId;
    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @ApiModelProperty("微信支付订单号")
    @JSONField(name = "transaction_id")
    private String transactionId;
    @ApiModelProperty("交易类型")
    @JSONField(name = "trade_type")
    private String tradeType;
    @ApiModelProperty("交易状态")
    @JSONField(name = "trade_state")
    private String tradeState;
    @ApiModelProperty("交易状态描述")
    @JSONField(name = "trade_state_desc")
    private String tradeStateDesc;
    @ApiModelProperty("付款银行")
    @JSONField(name = "bank_type")
    private String bankType;
    @ApiModelProperty("支付完成时间")
    @JSONField(name = "success_time")
    private String successTime;
    @ApiModelProperty("附加数据")
    @JSONField(name = "attach")
    private String attach;
    @ApiModelProperty("支付者")
    @JSONField(name = "payer")
    private Payer payer;
    @ApiModelProperty("订单金额")
    @JSONField(name = "amount")
    private Amount amount;
    @ApiModelProperty("优惠功能")
    @JSONField(name = "promotion_detail")
    private PromotionDetail promotionDetail;
    @ApiModelProperty("场景信息")
    @JSONField(name = "scene_info")
    private SceneInfo sceneInfo;
}
