package com.wx.pay;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wx.pay.common.CurrencyEnum;
import com.wx.pay.dto.AmountDto;
import com.wx.pay.dto.CreateOrderDto;
import com.wx.pay.dto.PayerDto;
import com.wx.pay.prop.PayProperties;
import com.wx.pay.util.FileUtil;
import okhttp3.HttpUrl;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.security.*;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class PayApplicationTests {

    @Autowired
    private PayProperties payProperties;
    @Autowired
    private HttpClient httpClient;

    //@Test
    void contextLoads() throws IOException {

    }


    //1.创建订单
    //@Test
    public void testCreateOrder() throws IOException {
        //请求URL
        HttpPost httpPost = new HttpPost(payProperties.getCreateOrder());
        CreateOrderDto createOrderDto = new CreateOrderDto();
        createOrderDto.setAppId(payProperties.getAppId());
        createOrderDto.setMchId(payProperties.getMchId());
        String outTradeNo = UUID.randomUUID().
                toString().replaceAll("-", "").toUpperCase();
        System.out.println("订单号"+outTradeNo);
        createOrderDto.setOutTradeNo(outTradeNo);
        createOrderDto.setDesc("Image形象店-深圳腾大-QQ公仔");
        createOrderDto.setNotifyUrl(payProperties.getPayOrderNotify());
        AmountDto amountDto = new AmountDto();
        amountDto.setCurrency(CurrencyEnum.CNY);
        amountDto.setTotal(1);
        createOrderDto.setAmountDto(amountDto);
        PayerDto payerDto = new PayerDto();
        payerDto.setOpenId(payProperties.getOpenId());
        createOrderDto.setPayerDto(payerDto);
        String reqParma = JSONObject.toJSONString(createOrderDto);
        System.out.println("reqParma = " + reqParma);

        StringEntity entity = new StringEntity(reqParma, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        //完成签名并执行请求
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
                //解析prepay_id
                Map map = JSONObject.parseObject(EntityUtils.toString(response.getEntity()), Map.class);
                String prepayId = (String) map.get("prepay_id");
                System.out.println("===============生成prepay_id =========");
                System.out.println(RSAUtil.getToken(prepayId));
            } else if (statusCode == 204) {
                System.out.println("success");
            } else {
                System.out.println("failed,resp code = " + statusCode + ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } finally {
            response.close();
        }

    }

    //2.查询订单
    @Test
    public void queryOrder() throws IOException {
        String url = payProperties.getQueryOrder();
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("4200001804202304176732537074");
        stringBuilder.append("?mchid="+payProperties.getMchId());

        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        StringEntity entity = new StringEntity("","UTF-8");
        entity.setContentType("application/json");
        httpGet.setHeader("Accept", "application/json");
        //完成签名并执行请求
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
                //解析prepay_id

            } else if (statusCode == 204) {
                System.out.println("success");
            } else {
                System.out.println("failed,resp code = " + statusCode + ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            response.close();
        }
    }


}
