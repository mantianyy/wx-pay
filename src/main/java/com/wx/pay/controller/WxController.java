package com.wx.pay.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wx.pay.common.*;
import com.wx.pay.dto.*;
import com.wx.pay.dto.refund.RefundDto;
import com.wx.pay.dto.refund.RefundGoods;
import com.wx.pay.dto.refund.RefundSourceDto;
import com.wx.pay.dto.refund_result.RefundNotifyDto;
import com.wx.pay.prop.PayProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@CrossOrigin
@RequestMapping("/create")
@RestController
@Api(tags = "微信模块")
@Slf4j
public class WxController {

    @Resource
    private PayProperties payProperties;
    @Resource
    private HttpClient httpClient;

    @ApiOperation(value = "统一下单接口")
    @PostMapping("/createOrder")
    public R createOrder() {
        HttpPost httpPost = new HttpPost(payProperties.getCreateOrder());
        CreateOrderDto createOrderDto = new CreateOrderDto();
        createOrderDto.setAppId(payProperties.getAppId());
        createOrderDto.setMchId(payProperties.getMchId());
        String outTradeNo = UUID.randomUUID().
                toString().replaceAll("-", "").toUpperCase();
        createOrderDto.setOutTradeNo(outTradeNo);
        createOrderDto.setDesc("Image形象店-深圳腾大-QQ公仔");
        createOrderDto.setNotifyUrl("https://68p076249i.zicp.fun/wx-paypay/create/result/notify");
        AmountDto amountDto = new AmountDto();
        amountDto.setCurrency(CurrencyEnum.CNY);
        amountDto.setTotal(1);
        createOrderDto.setAmountDto(amountDto);
        PayerDto payerDto = new PayerDto();
        payerDto.setOpenId("0d1AxOFa1n3z8F0WDUFa1POXwr3AxOFA");
        createOrderDto.setPayerDto(payerDto);
        String reqParma = JSONObject.toJSONString(createOrderDto);

        StringEntity entity = new StringEntity(reqParma, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");


        //完成签名并执行请求
        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpPost);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
                } else if (statusCode == 204) {
                    System.out.println("success");
                } else {
                    System.out.println("failed,resp code = " + statusCode + ",return body = " + EntityUtils.toString(response.getEntity()));

                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            log.info("error is {}", e.getLocalizedMessage());
            return R.error(ResultCode.ERROR, null, e.getLocalizedMessage());
        }
        return R.ok("下单成功", null);
    }

    @ApiOperation(value = "支付订单通知")
    @PostMapping("/result/notify")
    public Map payOrderNotify(@RequestBody Map map) {
        Map result = new HashMap();
        PayOrderNotifyDto payOrderNotifyDto = JSONObject.parseObject(JSON.toJSONString(map), PayOrderNotifyDto.class);
        if (ObjectUtil.isEmpty(payOrderNotifyDto)) {
            return R.error(ResultCode.ERROR, "支付订单通知参数异常", null);
        }
        ResourceDto resourceDto = payOrderNotifyDto.getResourceDto();
        if (ObjectUtil.isEmpty(resourceDto)) {
            return R.error(ResultCode.ERROR, "支付订单通知详情信息异常", null);
        }
        //解密信息
        String algorithm = resourceDto.getAlgorithm();
        String ciphertext = resourceDto.getCiphertext();
        String associatedData = resourceDto.getAssociatedData();
        String nonce = resourceDto.getNonce();
        String res = null;
        try {
            res = new AesUtil(payProperties.getApiV3Key().getBytes(StandardCharsets.UTF_8))
                    .decryptToString(associatedData.getBytes(StandardCharsets.UTF_8),
                            nonce.getBytes(StandardCharsets.UTF_8),
                            ciphertext);
        } catch (Exception e) {
            log.info("解密失敗 result {}", e.getLocalizedMessage());
        }

        if (res == null) {
            result.put("code", "FAIL");
            result.put("message", "通知失败,解密信息异常!");
            return result;
        }
        Map resMap = JSONObject.parseObject(res, Map.class);
        String transactionId = (String) resMap.get("transaction_id");
        if (StringUtils.isEmpty(transactionId)) {
            result.put("code", "FAIL");
            result.put("message", "通知失败,transaction_id为空!");
            return result;
        }
        result.put("code", "SUCCESS");
        result.put("message", "成功!");
        return result;
    }


    @ApiOperation(value = "单笔订单查询")
    @GetMapping("/result/queryOrder")
    public R queryOrder(String transactionId) throws IOException {
        String result = null;
        String url = payProperties.getQueryOrder();
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append(transactionId);
        stringBuilder.append("?mchid=" + payProperties.getMchId());
        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        StringEntity entity = new StringEntity("", "UTF-8");
        entity.setContentType("application/json");
        httpGet.setHeader("Accept", "application/json");
        //完成签名并执行请求
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
                //解析prepay_id
                result = EntityUtils.toString(response.getEntity());
            } else if (statusCode == 204) {
                System.out.println("success");
            } else {
                System.out.println("failed,resp code = " + statusCode + ",return body = " + EntityUtils.toString(response.getEntity()));
                return R.error("订单查询失败");
            }
        } catch (Exception e) {
            log.info("订单查询异常 {}",e.getLocalizedMessage());
            return R.error("订单查询异常");
        } finally {
            response.close();
        }
        return R.ok(JSON.parseObject(result));
    }

    @ApiOperation(value = "申请退款")
    @PostMapping("/result/refund")
    public R refundOrder(@RequestBody Map reqMap){
        Map map = null;
        String transactionId = (String) reqMap.get("transactionId");
        String outTradeNo = (String) reqMap.get("outTradeNo");
        if(StringUtils.isEmpty(transactionId)&&StringUtils.isEmpty(outTradeNo)){
            return R.error(ResultCode.ERROR, null, "transactionId or outTradeNo为空!");
        }
        com.wx.pay.dto.refund.RefundDto refundDto = new RefundDto();
        refundDto.setTransactionId(null);
        refundDto.setOutTradeNo(StringUtils.isEmpty(outTradeNo)?null:outTradeNo);
        refundDto.setOutRefundNo(RandomUtil.randomString(32).toString().toUpperCase());
        refundDto.setReason("商品没有货物");
        refundDto.setNotifyUrl(payProperties.getRefundOrderNotify());
        refundDto.setFundAccountEnum(FundAccountEnum.AVAILABLE);
        // TODO: 2023/4/17 from由于配置不能正常退款,暂不配置
        //1.设置from参数
        com.wx.pay.dto.refund.AmountDto amountDto = new com.wx.pay.dto.refund.AmountDto();
        amountDto.setRefund(1);
//        List<RefundSourceDto> fromList = new ArrayList<>();
//        RefundSourceDto refundSourceDto = new RefundSourceDto();
//        refundSourceDto.setAccount(AccountEnum.AVAILABLE);
//        refundSourceDto.setAmount(1);
//        fromList.add(refundSourceDto);
        amountDto.setFrom(null);
        //2.设置原订单金额
        amountDto.setTotal(1);
        amountDto.setCurrencyEnum(CurrencyEnum.CNY);
        //3.设置商品信息
        List<RefundGoods> list = new ArrayList<>();
        refundDto.setGoodsList(list);
        refundDto.setAmountDto(amountDto);

        //退款地址
        String refundOrderUrl = payProperties.getRefundOrder();
        String reqParam = JSON.toJSONString(refundDto);
        log.info("申请退款请求参数 {}",reqParam);
        HttpPost httpPost = new HttpPost(refundOrderUrl);
        StringEntity entity = new StringEntity(reqParam, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");


        //完成签名并执行请求
        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpPost);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("申请退款结果 {}",EntityUtils.toString(response.getEntity()));
                if (statusCode == 200) {
                    log.info("申请退款结果成功 {}",EntityUtils.toString(response.getEntity()));
                    String res = EntityUtils.toString(response.getEntity());
                    map = JSONObject.parseObject(res,Map.class);
                } else if (statusCode == 204) {
                    System.out.println("success");
                } else {
                    System.out.println("failed,resp code = " + statusCode + ",return body = " + EntityUtils.toString(response.getEntity()));
                }
            } finally {
                response.close();
            }
        } catch (IOException e) {
            log.info("error is {}", e.getLocalizedMessage());
            return R.error(ResultCode.ERROR, null, e.getLocalizedMessage());
        }
        String status = (String) map.get("status");
        if(!"SUCCESS".equals(status)){
            return R.error(ResultCode.ERROR, null, "订单申请失败");
        }
        return R.ok("订单申请退款成功", map);
    }


    @ApiOperation(value = "退款通知")
    @PostMapping("/result/refund-notify")
    public Map refundOrderNotify(@RequestBody Map map){
        Map result = new HashMap();
        RefundNotifyDto refundNotifyDto = JSONObject.parseObject(JSON.toJSONString(map), RefundNotifyDto.class);
        if (ObjectUtil.isEmpty(refundNotifyDto)) {
            return R.error(ResultCode.ERROR, "退款通知参数异常", null);
        }
        ResourceDto resourceDto = refundNotifyDto.getResource();
        if (ObjectUtil.isEmpty(resourceDto)) {
            return R.error(ResultCode.ERROR, "退款通知情信息异常", null);
        }
        //解密信息
        String ciphertext = resourceDto.getCiphertext();
        String associatedData = resourceDto.getAssociatedData();
        String nonce = resourceDto.getNonce();
        String res = null;
        try {
            res = new AesUtil(payProperties.getApiV3Key().getBytes(StandardCharsets.UTF_8))
                    .decryptToString(associatedData.getBytes(StandardCharsets.UTF_8),
                            nonce.getBytes(StandardCharsets.UTF_8),
                            ciphertext);
        } catch (Exception e) {
            log.info("解密失敗 result {}", e.getLocalizedMessage());
        }

        if (res == null) {
            result.put("code", "FAIL");
            result.put("message", "通知失败,解密信息异常!");
            return result;
        }
        Map resMap = JSONObject.parseObject(res, Map.class);
        String refund_status = (String) resMap.get("refund_status");
        if (StringUtils.isEmpty(refund_status)) {
            result.put("code", "FAIL");
            result.put("message", "通知失败,refund_status为空!");
            return result;
        }else if(!"SUCCESS".equals(refund_status)){
            result.put("code", "FAIL");
            result.put("message", "通知失败!");
            return result;
        }
        result.put("code", "SUCCESS");
        result.put("message", "退款成功!");
        return result;
    }

}
