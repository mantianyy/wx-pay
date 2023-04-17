package com.wx.pay.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class PayProperties {
    /**
     * 证书
     **/
    private String cert;
    /**
     * 私钥
     */
    private String privatekey;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 序列号
     */
    private String mchSerialno;
    /**
     * API V3密钥
     */
    private String apiV3Key;
    /**
     * 微信下单
     **/
    private String createOrder;
    /**
     * 查询订单
     **/
    private String queryOrder;
    /**
     * 关闭订单
     **/
    private String closeOrder;
    /**
     * 申请退款
     **/
    private String refundOrder;
    /**
     * 查询单笔退款
     **/
    private String refundQuery;
    /**
     * 申请交易账单
     **/
    private String applyTradebill;
    /**
     * 申请交易账单
     **/
    private String appId;
    /**
     * 申请交易账单
     **/
    private String secret;

    private String openId;

    private String payOrderNotify;

    private String refundOrderNotify;

}
