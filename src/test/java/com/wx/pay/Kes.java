package com.wx.pay;

import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wx.pay.util.FileUtil;
import okhttp3.HttpUrl;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

public class Kes {



    // Authorization: <schema> <token>
// GET - getToken("GET", httpurl, "")
// POST - getToken("POST", httpurl, json)



    public static String getToken(String method, HttpUrl url, String body) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        String nonceStr = "your nonce string";
        long timestamp = System.currentTimeMillis() / 1000;
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        String signature = sign(message.getBytes("utf-8"));

        return "mchid=\"" + "1641770912" + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + "670CFBD729DE9AC29CA51886E11F0B2A0956B69D" + "\","
                + "signature=\"" + signature + "\"";
    }

    public static String sign(byte[] message) throws NoSuchAlgorithmException, IOException, InvalidKeyException, SignatureException {
        String path = ResourceUtils.getFile("classpath:" + "cert/apiclient_key.pem").getPath();
        if(StringUtils.isEmpty(path)){
            throw new RuntimeException("文件路径不存在");
        }
        String privateKey = FileUtil.getValue(path);
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(privateKey);
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(merchantPrivateKey);
        sign.update(message);
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    public static String buildMessage(String method, HttpUrl url, long timestamp, String nonceStr, String body) {
        String canonicalUrl = url.encodedPath();
        if (url.encodedQuery() != null) {
            canonicalUrl += "?" + url.encodedQuery();
        }

        return method + "\n"
                + canonicalUrl + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + body + "\n";
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        String schema = "WECHATPAY2-SHA256-RSA2048";
        HttpUrl httpurl = HttpUrl.parse("https://api.mch.weixin.qq.com/v3/certificates");


        // Authorization: <schema> <token>
// GET - getToken("GET", httpurl, "")
// POST - getToken("POST", httpurl, json)
        System.out.println(getToken("GET",httpurl,""));
    }

}
