package com.wx.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wx.common.CurrencyEnum;
import com.wx.common.R;
import com.wx.common.ResultCode;
import com.wx.dto.apply.AppleFundBillDto;
import com.wx.dto.apply.AppleTradeBillDto;
import com.wx.dto.apply.ApplyRefundDto;
import com.wx.dto.create.CreateOrderDto;
import com.wx.dto.notify.NotifyPayEncryptDto;
import com.wx.dto.query.MerchantOrderQueryDto;
import com.wx.dto.query.PayOrderQueryDto;
import com.wx.dto.query.QuerySingleRefundDto;
import com.wx.dto.refund.RefundResultNotificationEncryptDto;
import com.wx.dto.sign.SignDto;
import com.wx.dto.wx.dto.PayerDto;
import com.wx.properties.PayProperties;
import com.wx.util.RSAUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
import java.util.*;

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
    public Map payNotify(@RequestBody NotifyPayEncryptDto notifyPayEncryptDto) {
        return payNotifyProcess(notifyPayEncryptDto);
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
    public Map refundResultNotify(@RequestBody RefundResultNotificationEncryptDto refundResultNotificationEncryptDto) {
        return refundResultNotifyProcess(refundResultNotificationEncryptDto);
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

    @PostMapping(value = "/sign")
    @ApiOperation(value = "10.获取签名")
    public R getSign(@RequestBody SignDto signDto){
        Map resMap;
        if(ObjectUtil.isEmpty(signDto)){
            return R.error("参数为空");
        }

        if(StringUtils.isEmpty(signDto.getSignType())){
            return R.error("sign_type must not empty");
        }

        if(StringUtils.isEmpty(signDto.getValue())){
            return R.error("value must not empty");
        }
        try{
            if(signDto.getSignType().equals("0")){
                resMap = RSAUtil.getSignByDownloadUrl(signDto.getValue(),payProperties);
            }else{
                resMap = RSAUtil.getSignByPrePayId(signDto.getValue(),payProperties);
            }
        }catch (Exception e){
            String errorMsg = ExceptionUtil.getMessage(e);
            log.info("getSign error is {}", errorMsg);
            return R.error(errorMsg);
        }
        return R.ok(resMap);
    }

    private R createOrderProcess(CreateOrderDto createOrderDto) {
        if(ObjectUtil.isEmpty(createOrderDto)){
            return R.error("参数不能为空!");
        }

        if(ObjectUtil.isEmpty(createOrderDto.getPayer())){
            return R.error("payer不能为空!");
        }
        HttpPost httpPost = new HttpPost(payProperties.getCreateOrder());
//        createOrderDto.setOpenId(payProperties.getOpenId());
//        createOrderDto.setAppId(payProperties.getAppId());
//        createOrderDto.setMchId(payProperties.getMchId());
//        String outTradeNo = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
//        createOrderDto.setOutTradeNo(outTradeNo);
//        createOrderDto.setNotifyUrl(payProperties.getPayOrderNotify());

        com.wx.dto.wx.dto.CreateOrderDto createOrderDto1 = new com.wx.dto.wx.dto.CreateOrderDto();
        createOrderDto1.setAppId(payProperties.getAppId());
        createOrderDto1.setMchId(payProperties.getMchId());
        String outTradeNo = UUID.randomUUID().
                toString().replaceAll("-", "").toUpperCase();
        createOrderDto1.setOutTradeNo(outTradeNo);
        createOrderDto1.setDesc("Image形象店-深圳腾大-QQ公仔");
        createOrderDto1.setNotifyUrl(payProperties.getPayOrderNotify());

        com.wx.dto.wx.dto.AmountDto amountDto = new com.wx.dto.wx.dto.AmountDto();
        amountDto.setCurrency(CurrencyEnum.CNY);
        amountDto.setTotal(1);
        createOrderDto1.setAmountDto(amountDto);
        PayerDto payerDto = new PayerDto();
        payerDto.setOpenId("oUYWT5AvRQgX1Z9prDMqXrwYY_wk");
        createOrderDto1.setPayerDto(payerDto);

        String reqParma = JSONObject.toJSONString(createOrderDto1);
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
                    return R.ok("下单成功", EntityUtils.toString(response.getEntity()));
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

    private R payOrderQueryProcess(PayOrderQueryDto payOrderQueryDto) {
        if(ObjectUtil.isEmpty(payOrderQueryDto)){
            return R.error("参数为空");
        }
        if(StringUtils.isEmpty(payOrderQueryDto.getTransactionId())){
            return R.error("transactionId 为空!");
        }
        String result = null;
        String url = payProperties.getQueryOrder();
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append(payOrderQueryDto.getTransactionId());
        stringBuilder.append("?mchid=" + payProperties.getMchId());
        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        StringEntity entity = new StringEntity("", "UTF-8");
        entity.setContentType("application/json");
        httpGet.setHeader("Accept", "application/json");
        //完成签名并执行请求
        CloseableHttpResponse response=null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpGet);
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
            log.info("订单查询异常 {}", e.getLocalizedMessage());
            return R.error("订单查询异常");
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return R.ok(JSON.parseObject(result));
    }

    private R merchantOrderQueryProcess(MerchantOrderQueryDto merchantOrderQueryDto) {
        if(ObjectUtil.isEmpty(merchantOrderQueryDto)){
            return R.error("参数为空");
        }
        if(StringUtils.isEmpty(merchantOrderQueryDto.getOutTradeNo())){
            return R.error("outTradeNo 为空!");
        }
        String result = null;
        String url = payProperties.getQueryOrder();
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append(merchantOrderQueryDto.getOutTradeNo());
        stringBuilder.append("?mchid=" + payProperties.getMchId());
        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        StringEntity entity = new StringEntity("", "UTF-8");
        entity.setContentType("application/json");
        httpGet.setHeader("Accept", "application/json");
        //完成签名并执行请求
        CloseableHttpResponse response=null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpGet);
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
            log.info("订单查询异常 {}", e.getLocalizedMessage());
            return R.error("订单查询异常");
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return R.ok(JSON.parseObject(result));
    }

    private Map payNotifyProcess(NotifyPayEncryptDto notifyPayEncryptDto) {
        Map result = new HashMap();
        if (ObjectUtil.isEmpty(notifyPayEncryptDto)) {
            result.put("code", "FAIL");
            result.put("message", "参数传递异常!");
            return result;
        }
        NotifyPayEncryptDto.Resource resourceDto = notifyPayEncryptDto.getResource();
        if (ObjectUtil.isEmpty(resourceDto)) {
            result.put("code", "FAIL");
            result.put("message", "参数传递异常!");
            return result;
        }
        //解密信息
        String algorithm = resourceDto.getAlgorithm();
        String ciphertext = resourceDto.getCiphertext();
        String associatedData = resourceDto.getAssociated_data();
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

    private R applyRefundProcess(ApplyRefundDto applyRefundDto) {
        Map result = new HashMap();
        Map map = new HashMap();
        if(ObjectUtil.isEmpty(applyRefundDto)){
            return R.error("参数为空");
        }
        if(StringUtils.isEmpty(applyRefundDto.getOutTradeNo())){
            return R.error("商户退款单号参数为空");
        }
        applyRefundDto.setOutRefundNo(RandomUtil.randomString(32).toString().toUpperCase());
        applyRefundDto.setNotifyUrl(payProperties.getRefundOrderNotify());
        //退款地址
        String refundOrderUrl = payProperties.getRefundOrder();
        String reqParam = JSON.toJSONString(applyRefundDto);
        log.info("申请退款请求参数 {}", reqParam);
        HttpPost httpPost = new HttpPost(refundOrderUrl);
        StringEntity entity = new StringEntity(reqParam, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        //退款请求
        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpPost);
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                log.info("申请退款结果 {}", EntityUtils.toString(response.getEntity()));
                if (statusCode == 200) {
                    log.info("申请退款结果成功 {}", EntityUtils.toString(response.getEntity()));
                    String res = EntityUtils.toString(response.getEntity());
                    map = JSONObject.parseObject(res, Map.class);

                    String status = (String) map.get("status");
                    if (!"SUCCESS".equals(status)) {
                        return R.error(ResultCode.ERROR, null, EntityUtils.toString(response.getEntity()));
                    }
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

        return R.ok("订单申请退款成功", map);
    }

    private R querySingleRefundProcess(QuerySingleRefundDto querySingleRefundDto) {return null;}

    private Map refundResultNotifyProcess(RefundResultNotificationEncryptDto refundResultNotificationEncryptDto) {
        Map result = new HashMap();

        if (ObjectUtil.isEmpty(refundResultNotificationEncryptDto)) {
            return R.error(ResultCode.ERROR, "退款通知参数异常", null);
        }
        RefundResultNotificationEncryptDto.Resource resource = refundResultNotificationEncryptDto.getResource();
        if (ObjectUtil.isEmpty(resource)) {
            return R.error(ResultCode.ERROR, "退款通知情信息异常", null);
        }
        //解密信息
        String ciphertext = resource.getCiphertext();
        String associatedData = resource.getAssociated_data();
        String nonce = resource.getNonce();
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
        } else if (!"SUCCESS".equals(refund_status)) {
            result.put("code", "FAIL");
            result.put("message", "通知失败!");
            return result;
        }
        result.put("code", "SUCCESS");
        result.put("message", "退款成功!");
        return result;
    }

    private R applyTransactionBillProcess(AppleFundBillDto appleFundBillDto){return null;}

    private R applyFundBillProcess(AppleTradeBillDto  appleTradeBillDto){return null;}

    private Map downloadBillProcess(Map map){
        return null;
    }




}
