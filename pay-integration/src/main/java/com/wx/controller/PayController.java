package com.wx.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wx.common.R;
import com.wx.common.ResultCode;
import com.wx.dto.apply.AppleFundBillDto;
import com.wx.dto.apply.AppleTradeBillDto;
import com.wx.dto.apply.ApplyRefundDto;
import com.wx.dto.create.CreateOrderDto;
import com.wx.dto.notify.NotifiyPayResultDto;
import com.wx.dto.query.MerchantOrderQueryDto;
import com.wx.dto.query.PayOrderQueryDto;
import com.wx.dto.query.QuerySingleRefundDto;
import com.wx.dto.refund.RefundResultNotificationDto;
import com.wx.properties.PayProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pay")
@Api(tags = "微信支付接口测试文档")
@Slf4j
public class PayController {

    @Autowired
    private PayProperties payProperties;
    @Resource
    private HttpClient httpClient;

    @PostMapping(value = "/createOrder")
    @ApiOperation(value = "1.下单")
    public R createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return createOrderProcess(createOrderDto);
    }


    @PostMapping(value = "/payNotify")
    @ApiOperation(value = "2.支付通知")
    public R payNotify(@RequestBody NotifiyPayResultDto notifiyPayResultDto) {
        return payNotifyProcess(notifiyPayResultDto);
    }

    @PostMapping(value = "/payOrderQuery")
    @ApiOperation(value = "3.1 微信支付订单信息查询")
    public R payOrderQuery(@RequestBody PayOrderQueryDto payOrderQueryDto) {
        return payOrderQueryProcess(payOrderQueryDto);
    }


    @PostMapping(value = "/merchantOrderQuery")
    @ApiOperation(value = "3.2 商户订单信息查询")
    public R merchantOrderQuery(@RequestBody MerchantOrderQueryDto merchantOrderQueryDto) {
        return merchantOrderQueryProcess(merchantOrderQueryDto);
    }

    @PostMapping(value = "/applyRefund")
    @ApiOperation(value = "4.申请退款")
    public R applyRefund(@RequestBody ApplyRefundDto applyRefundDto) {
        return applyRefundProcess(applyRefundDto);
    }


    @PostMapping(value = "/refundResultNotify")
    @ApiOperation(value = "5.退款结果通知")
    public R refundResultNotify(@RequestBody RefundResultNotificationDto refundResultNotificationDto) {
        return refundResultNotifyProcess(refundResultNotificationDto);
    }

    @PostMapping(value = "/querySingleRefund")
    @ApiOperation(value = "6.查询单笔退款")
    public R querySingleRefund(@RequestBody QuerySingleRefundDto querySingleRefundDto) {
        return querySingleRefundProcess(querySingleRefundDto);
    }


    @PostMapping(value = "/applyTransactionBill")
    @ApiOperation(value = "7.申请交易账单")
    public R applyTransactionBill(@RequestBody AppleFundBillDto appleFundBillDto) {
        return applyTransactionBillProcess(appleFundBillDto);
    }

    @PostMapping(value = "/applyFundBill")
    @ApiOperation(value = "8.申请资金账单")
    public R applyFundBill(@RequestBody AppleTradeBillDto appleTradeBillDto) {
        return applyFundBillProcess(appleTradeBillDto);
    }

    @PostMapping(value = "/downloadBill")
    @ApiOperation(value = "9.下载账单")
    public R downloadBill(@RequestBody Map map) {
        Map res =  downloadBillProcess(map);
        return null;
    }


    private R createOrderProcess(CreateOrderDto createOrderDto) {
        if (StringUtils.isEmpty(createOrderDto.getOpenId())) {
            return R.error(ResultCode.ERROR, null, "openid为空");
        }
        HttpPost httpPost = new HttpPost(payProperties.getCreateOrder());
        createOrderDto.setAppId(payProperties.getAppId());
        createOrderDto.setMchId(payProperties.getMchId());
        String outTradeNo = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        createOrderDto.setOutTradeNo(outTradeNo);
        createOrderDto.setDescription("Image形象店-深圳腾大-QQ公仔");
        createOrderDto.setNotifyUrl(payProperties.getPayOrderNotify());
        //1.订单金额信息
        CreateOrderDto.Amount amountDto = new CreateOrderDto.Amount();
        amountDto.setCurrency(createOrderDto.getAmount().getCurrency());
        amountDto.setTotal(createOrderDto.getAmount().getTotal());
        createOrderDto.setAmount(amountDto);
        //2.支付者
        CreateOrderDto.Payer payerDto = new CreateOrderDto.Payer();
        payerDto.setOpenId(createOrderDto.getOpenId());
        createOrderDto.setPayer(payerDto);

        String reqParma = JSONObject.toJSONString(createOrderDto);
        StringEntity entity = new StringEntity(reqParma, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        //2.请求地址
        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpPost);
            try {
                //3.返回结果
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    log.info("success,return body = " + EntityUtils.toString(response.getEntity()));
                    return R.ok("下单成功", null);
                } else if (statusCode == 204) {
                    log.info("success");
                    return R.ok("下单异常", null);
                } else {
                    log.info("failed,resp code = " + statusCode + ",return body = " + EntityUtils.toString(response.getEntity()));
                    return R.ok("下单失败", null);
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            log.info("error is {}", e.getLocalizedMessage());
            return R.error(ResultCode.ERROR, null, e.getLocalizedMessage());
        }
    }

    private R payOrderQueryProcess(PayOrderQueryDto payOrderQueryDto) {return null;}

    private R merchantOrderQueryProcess(MerchantOrderQueryDto merchantOrderQueryDto) {return null;}

    private R payNotifyProcess(NotifiyPayResultDto notifiyPayResultDto) {
       return null;
    }

    private R applyRefundProcess(ApplyRefundDto applyRefundDto) {return null;}

    private R querySingleRefundProcess(QuerySingleRefundDto querySingleRefundDto) {return null;}

    private R refundResultNotifyProcess(RefundResultNotificationDto refundResultNotificationDto) {return null;}

    private R applyTransactionBillProcess(AppleFundBillDto appleFundBillDto){return null;}

    private R applyFundBillProcess(AppleTradeBillDto  appleTradeBillDto){return null;}

    private Map downloadBillProcess(Map map){
        return null;
    }


}
