package com.wx.pay.util;

import cn.hutool.core.util.RandomUtil;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import okhttp3.HttpUrl;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.*;
import java.util.Base64;

public class RSAUtil {

    String schema = "WECHATPAY2-SHA256-RSA2048";
    HttpUrl httpurl = HttpUrl.parse("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");


    public static String getMessage(String prepayId) {
        String appid = "wx6f8b36de02f6af65";
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String random = RandomUtil.randomString(32);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appid).append("\n");
        stringBuilder.append(timeStamp).append("\n");
        stringBuilder.append(random).append("\n");
        stringBuilder.append("prepay_id=" + prepayId).append("\n");
        System.out.println("message========");
        System.out.println("结果" + stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static String getToken(String prepayId) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        String nonceStr = RandomUtil.randomString(32);
        long timestamp = System.currentTimeMillis() / 1000;
        String path = ResourceUtils.getFile("classpath:" + "cert/apiclient_key.pem").getPath();
        if (StringUtils.isEmpty(path)) {
            throw new RuntimeException("文件路径不存在");
        }
        String privateKeyString = FileUtil.getValue(path);
        PrivateKey privateKey = PemUtil.loadPrivateKey(privateKeyString);
        String signature = sign(getMessage(prepayId).getBytes("utf-8"), privateKey);
        System.out.println("签名");
        System.out.println(signature);
        return "mchid=\"" + 1641770912 + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + "670CFBD729DE9AC29CA51886E11F0B2A0956B69D" + "\","
                + "signature=\"" + signature + "\"";
    }

    public static String sign(byte[] message, PrivateKey privateKey) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(message);

        return Base64.getEncoder().encodeToString(sign.sign());
    }

    /**
     * 对下载进行签名
     */
    public static String getToken2(String download_url,String nonceStr) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        long timestamp = System.currentTimeMillis() / 1000;
        String path = ResourceUtils.getFile("classpath:" + "cert/apiclient_key.pem").getPath();
        if (StringUtils.isEmpty(path)) {
            throw new RuntimeException("文件路径不存在");
        }
        String privateKeyString = FileUtil.getValue(path);
        PrivateKey privateKey = PemUtil.loadPrivateKey(privateKeyString);
        String signature = sign(getMessage(download_url).getBytes("utf-8"), privateKey);
        System.out.println("对download_url签名");
        System.out.println(signature);
        return "mchid=\"" + 1641770912 + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + "670CFBD729DE9AC29CA51886E11F0B2A0956B69D" + "\","
                + "signature=\"" + signature + "\"";
    }

    public static String getMessage2(String download_url) {
        String appid = "wx6f8b36de02f6af65";
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String random = RandomUtil.randomString(32);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appid).append("\n");
        stringBuilder.append(timeStamp).append("\n");
        stringBuilder.append(random).append("\n");
        stringBuilder.append("prepay_id=" + download_url).append("\n");
        System.out.println("message========");
        System.out.println("结果" + stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        //System.out.println(getToken2("https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx"));
    }

}
