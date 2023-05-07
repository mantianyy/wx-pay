package com.wx.dto.create;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.wx.dto.apply.ApplyRefundDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author jiang漫天
 */
@Data
@ApiModel("关闭订单接口参数模型")
public class CreateOrderDto {

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
    static class Detail {
        @Data
        static class GoodsDetail {
            @ApiModelProperty("商户侧商品编码")
            @JSONField(name = "merchant_goods_id")
            private Integer merchantGoodsId;
            @ApiModelProperty("微信支付商品编码")
            @JSONField(name = "wechatpay_goods_id")
            private String wechatpayGoodsId;
            @ApiModelProperty("商品名称")
            @JSONField(name = "goods_name")
            private String goodsName;
            @ApiModelProperty("商品数量")
            @JSONField(name = "quantity")
            private Integer quantity;
            @ApiModelProperty("商品单价")
            @JSONField(name = "unit_price")
            private Integer unitPrice;
        }

        @ApiModelProperty("订单原价")
        @JSONField(name = "cost_price")
        private Integer costPrice;
        @ApiModelProperty("商品小票ID")
        @JSONField(name = "invoice_id")
        private String invoiceId;
        @ApiModelProperty("单品列表")
        @JSONField(name = "goods_detail")
        private List<GoodsDetail> goodsDetail;
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
    @ApiModelProperty("商品描述")
    @JSONField(name = "description")
    private String description;
    @ApiModelProperty("商户订单号")
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @ApiModelProperty("交易结束时间")
    @JSONField(name = "time_expire")
    private String timeExpire;
    @ApiModelProperty("附加数据")
    @JSONField(name = "attach")
    private String attach;
    @ApiModelProperty("通知地址")
    @JSONField(name = "notify_url")
    private String notifyUrl;
    @ApiModelProperty("订单优惠标记")
    @JSONField(name = "goods_tag")
    private String goodsTag;
    @ApiModelProperty("电子发票入口开放标识")
    @JSONField(name = "support_fapiao")
    private Boolean supportFaPiao;
    @ApiModelProperty("订单金额")
    @JSONField(name = "amount")
    private Amount amount;
    @ApiModelProperty("支付者")
    @JSONField(name = "payer")
    private Payer payer;
    @ApiModelProperty("优惠功能")
    @JSONField(name = "detail")
    private Detail detail;
    @ApiModelProperty("场景信息")
    @JSONField(name = "scene_info")
    private SceneInfo sceneInfo;
    @ApiModelProperty("结算信息")
    @JSONField(name = "settle_info")
    private SettleInfo settleInfo;
}
