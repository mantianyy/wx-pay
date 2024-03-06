package com.wx.config;


import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wx.controller.PayController;
import com.wx.properties.PayProperties;
import com.wx.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

/**
 * @author jiang漫天
 */
@Configuration
public class PayConfig {
    Logger logger = LoggerFactory.getLogger(PayConfig.class);
    @Resource
    private PayProperties payProperties;
    private CertificatesManager certificatesManager;
    private HttpHost proxy;
    private HttpClient httpClient;
    private Verifier verifier;

    @Bean
    public HttpClient httpClient() throws Exception {
        logger.info("测试开始 httpClient");
        logger.info("cert is1 {} ",payProperties.getCert());
        logger.info("cert is2 {} ",payProperties.getCert());
        org.springframework.core.io.Resource resource = new ClassPathResource(payProperties.getCert());
        logger.info("cert is3 {} ",payProperties.getCert());
        if(StringUtils.isEmpty(resource.getFile().getPath())){
            throw new RuntimeException("文件路径不存在");
        }
        logger.info("cert is4 {} ",payProperties.getCert());
        String privateKey = IOUtils.toString(new BufferedInputStream(resource.getInputStream()));
        logger.info("cert is5 {} ",payProperties.getCert());
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(privateKey);
        // 获取证书管理器实例
        certificatesManager = CertificatesManager.getInstance();
        // 添加代理服务器
        certificatesManager.setProxy(proxy);
        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(payProperties.getMchId(), new WechatPay2Credentials(payProperties.getMchId(),
                        new PrivateKeySigner(payProperties.getMchSerialno(), merchantPrivateKey)),
                //payProperties.getBytes(StandardCharsets.UTF_8)
                payProperties.getApiV3Key().getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier
        verifier = certificatesManager.getVerifier(payProperties.getMchId());
        // 构造httpclient
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(payProperties.getMchId(), payProperties.getMchSerialno(), merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier))
                .build();
        return httpClient;
    }


    @Bean
    public WechatPayHttpClientBuilder wechatPayHttpClientBuilder() throws Exception {
        logger.info("测试开始 wechatPayHttpClientBuilder");
        logger.info("cert is {} ",payProperties.getCert());
        org.springframework.core.io.Resource resource = new ClassPathResource(payProperties.getCert());
        if(StringUtils.isEmpty(resource.getFile().getPath())){
            throw new RuntimeException("文件路径不存在");
        }
        String privateKey = IOUtils.toString(resource.getInputStream());
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(privateKey);
        // 获取证书管理器实例
        certificatesManager = CertificatesManager.getInstance();
        // 添加代理服务器
        certificatesManager.setProxy(proxy);
        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(payProperties.getMchId(), new WechatPay2Credentials(payProperties.getMchId(),
                        new PrivateKeySigner(payProperties.getMchSerialno(), merchantPrivateKey)),
                //payProperties.getBytes(StandardCharsets.UTF_8)
                payProperties.getApiV3Key().getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier
        verifier = certificatesManager.getVerifier(payProperties.getMchId());
        // 构造httpclient
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(payProperties.getMchId(), payProperties.getMchSerialno(), merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier))
                .build();
        WechatPayHttpClientBuilder wechatPayHttpClientBuilder =  WechatPayHttpClientBuilder.create().
                withMerchant(payProperties.getMchId(), payProperties.getMchSerialno(), merchantPrivateKey)
                .withValidator((response) -> true);
        return wechatPayHttpClientBuilder;
    }
}
