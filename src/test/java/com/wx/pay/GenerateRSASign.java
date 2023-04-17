package com.wx.pay;

import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wx.pay.util.FileUtil;
import okhttp3.HttpUrl;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class GenerateRSASign {


    // Authorization: <schema> <token>
// GET - getToken("GET", httpurl, "")
// POST - getToken("POST", httpurl, json)
    String schema = "WECHATPAY2-SHA256-RSA2048";
    HttpUrl httpurl = HttpUrl.parse("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");




}
