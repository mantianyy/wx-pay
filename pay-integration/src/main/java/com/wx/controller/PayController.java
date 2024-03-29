package com.wx.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wx.common.CurrencyEnum;
import com.wx.common.R;
import com.wx.common.ResultCode;
import com.wx.common.TransactionBillEnum;
import com.wx.dto.apply.ApplyFundBillDto;
import com.wx.dto.apply.ApplyTradeBillDto;
import com.wx.dto.apply.ApplyRefundDto;
import com.wx.dto.bill.BillDto;
import com.wx.dto.bill.BillExcelDto;
import com.wx.dto.create.CreateOrderDto;
import com.wx.dto.notify.NotifyPayEncryptDto;
import com.wx.dto.query.MerchantOrderQueryDto;
import com.wx.dto.query.PayOrderQueryDto;
import com.wx.dto.query.QuerySingleRefundDto;
import com.wx.dto.refund.RefundResultNotificationEncryptDto;
import com.wx.dto.sign.SignDto;
import com.wx.properties.PayProperties;
import com.wx.util.RSAUtil;
import com.wx.util.StreamUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping(value = "/pay")
@Api(tags = "微信支付接口测试文档")
@Slf4j
public class PayController {
    Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private PayProperties payProperties;
    @Resource
    private HttpClient httpClient;
    @Resource
    private WechatPayHttpClientBuilder wechatPayHttpClientBuilder;

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
    public Map applyTransactionBill(@RequestBody ApplyTradeBillDto applyTradeBillDto) {
        return applyTransactionBillProcess(applyTradeBillDto);
    }

    @PostMapping(value = "/applyFundBill")
    @ApiOperation(value = "8.申请资金账单")
    public Map applyFundBill(@RequestBody ApplyFundBillDto applyTradeBillDto) {
        return applyFundBillProcess(applyTradeBillDto);
    }

    @PostMapping(value = "/downloadBill")
    @ApiOperation(value = "9.下载账单")
    public void downloadBill(@RequestParam(name = "url")String url, HttpServletResponse httpResponse) throws IOException {
        downloadBillProcess(url,httpResponse);
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

    @GetMapping(value = "/info")
    @ApiOperation(value = "11.日志测试")
    public R info() {
        MDC.put("requestId", UUID.randomUUID().toString().toLowerCase());
        logger.info("测试接口 {}", "test");
        logger.error("测试接口 error {}","错误");
        logger.warn("测试接口 waring {}","警告");
        return R.ok("日志测试",null);
    }

    private R createOrderProcess(CreateOrderDto createOrderDto) {
        if(ObjectUtil.isEmpty(createOrderDto)){
            return R.error("参数不能为空!");
        }

        if(ObjectUtil.isEmpty(createOrderDto.getPayer())){
            return R.error("payer不能为空!");
        }
        HttpPost httpPost = new HttpPost(payProperties.getCreateOrder());
        createOrderDto.setOpenId(payProperties.getOpenId());
        createOrderDto.setAppId(payProperties.getAppId());
        createOrderDto.setMchId(payProperties.getMchId());
        String outTradeNo = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        createOrderDto.setOutTradeNo(outTradeNo);
        createOrderDto.setNotifyUrl(payProperties.getPayOrderNotify());

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

    @SneakyThrows
    private R querySingleRefundProcess(QuerySingleRefundDto querySingleRefundDto) {
        if (StringUtils.isEmpty(querySingleRefundDto.getOutRefundNo())) {
            return R.error("outRefundNo 为空");
        }
        String refundQueryUrl = payProperties.getRefundQuery().replaceAll("\\{out-refund-no}", "");
        StringBuilder stringBuilder = new StringBuilder(refundQueryUrl);
        stringBuilder.append(querySingleRefundDto.getOutRefundNo());

        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        StringEntity entity = new StringEntity("", "UTF-8");
        entity.setContentType("application/json");
        httpGet.setHeader("Accept", "application/json");

        String jsonRes = null;
        CloseableHttpResponse response = null;
        //查询单笔退款信息
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
                //解析prepay_id
                jsonRes = EntityUtils.toString(response.getEntity());
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
            response.close();
        }
        return R.ok(JSON.parseObject(jsonRes));
    }

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

    @SneakyThrows
    private Map applyTransactionBillProcess(ApplyTradeBillDto applyTradeBillDto){
        if(ObjectUtil.isEmpty(applyTradeBillDto)){
            return R.error("参数为空");
        }
        if (StringUtils.isEmpty(applyTradeBillDto.getBillDate())) {
            return R.error("bill_date 为空");
        }
        String applyTradebillUrl = payProperties.getApplyTradebill();
        StringBuilder stringBuilder = new StringBuilder(applyTradebillUrl);
        stringBuilder.append("?bill_date=" + applyTradeBillDto.getBillDate());
        stringBuilder.append("&bill_type=ALL");
        stringBuilder.append("&tar_type=GZIP");

        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        StringEntity entity = new StringEntity("", "UTF-8");
        entity.setContentType("application/json");
        httpGet.setHeader("Accept", "application/json");

        String jsonRes = null;
        CloseableHttpResponse response = null;
        //查询单笔退款信息
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
                //解析prepay_id
                jsonRes = EntityUtils.toString(response.getEntity());
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
            response.close();
        }
        return R.ok(JSON.parseObject(jsonRes));
    }

    @SneakyThrows
    private Map applyFundBillProcess(ApplyFundBillDto applyFundBillDto){
        if (ObjectUtil.isEmpty(applyFundBillDto)) {
            return R.error("参数为空");
        }
        if (StringUtils.isEmpty(applyFundBillDto.getBillDate())) {
            return R.error("bill_date 为空");
        }
        String applyTradebillUrl = payProperties.getApplyFundbill();
        StringBuilder stringBuilder = new StringBuilder(applyTradebillUrl);
        stringBuilder.append("?bill_date=" + applyFundBillDto.getBillDate());
        stringBuilder.append("&account_type=BASIC");
        stringBuilder.append("&tar_type=" + applyFundBillDto.getTarType());

        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        StringEntity entity = new StringEntity("", "UTF-8");
        entity.setContentType("application/json");
        httpGet.setHeader("Accept", "application/json");

        String jsonRes = null;
        CloseableHttpResponse response = null;
        //查询单笔退款信息
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
                //解析prepay_id
                jsonRes = EntityUtils.toString(response.getEntity());
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
            response.close();
        }
        return R.ok(JSON.parseObject(jsonRes));
    }

    @SneakyThrows
    private void downloadBillProcess(String url, HttpServletResponse httpResponse) {
        if(StringUtils.isEmpty(url)){
            httpResponse.getWriter().write(JSON.toJSONString(R.error("downloadUrl字段缺失")));
            return;
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "application/json");
        //请求下载地址
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = wechatPayHttpClientBuilder.build();
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (!ObjectUtil.isEmpty(entity)) {
                String fileName = URLEncoder.encode("交易账单", "UTF-8");
                httpResponse.setContentType("application/vnd.ms-excel");
                httpResponse.setCharacterEncoding("utf-8");
                httpResponse.setContentType("application/force-download");
                httpResponse.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                httpResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
                httpResponse.setHeader("Content-Type","application/octet-stream;charset=utf-8");
                String contentEncoding = entity.getContentType().getValue();
                InputStream stream = entity.getContent();
                InputStream inputStream = StreamUtils.getInputStream(stream, contentEncoding);
                String bodyAsString = StreamUtils.stream2String(inputStream);
                log.info("result is {}",bodyAsString);
                if(bodyAsString.contains("INVALID_REQUEST")){
                    httpResponse.getWriter().write(JSON.toJSONString(R.error("文件下载失败")));
                    return;
                }
                List<String> strings = Arrays.asList(bodyAsString.split("\n"));
                List<List<String>> dataList = new ArrayList<>();
                strings.forEach(s->{
                    List<String> data = Arrays.asList(s.replaceAll("`","").toString().split(","));
                    final int[] i = {0};
                    data.forEach(raw->{
                        String value = TransactionBillEnum.getKey(raw);
                        data.set(i[0],StringUtils.isEmpty(value)?raw:value);
                        i[0]++;
                    });
                    dataList.add(data);
                });
                dataList.remove(0);
                EasyExcel.write(httpResponse.getOutputStream())
                        .head(BillExcelDto.class)
                        .excelType(ExcelTypeEnum.XLSX)
                        .sheet("交易账单")
                        .doWrite(dataList);
            } else {
                httpResponse.getWriter().write(JSON.toJSONString(R.error("文件下载失败")));
            }
        } catch (Exception e) {
            log.info("文件下载失败 {}", e.getLocalizedMessage());
            httpResponse.getWriter().write(JSON.toJSONString(R.error("文件下载失败")));
        } finally {
            response.close();
        }
    }




}
