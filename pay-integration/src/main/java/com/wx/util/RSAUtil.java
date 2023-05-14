package com.wx.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wx.properties.PayProperties;
import okhttp3.HttpUrl;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.*;
import java.util.Base64;
import java.util.Map;

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
        String mchid = "1641770912";
        String serial_no = "670CFBD729DE9AC29CA51886E11F0B2A0956B69D";
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String signature = sign(getMessage2(download_url,mchid,nonceStr,timeStamp,serial_no).getBytes("utf-8"), privateKey);
        System.out.println("对download_url签名");
        System.out.println(signature);
        return "mchid="+"1641770912,nonce_str="+nonceStr+",signature="+signature+",timestamp="
                +timestamp+",serial_no="+"670CFBD729DE9AC29CA51886E11F0B2A0956B69D";
    }

    public static String getMessage2(String download_url,String mchid,String nonceStr,String timesStamp,String serial_no) {
//        String appid = "wx6f8b36de02f6af65";
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
//        String random = RandomUtil.randomString(32);
        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(appid).append("\n");
        stringBuilder.append(mchid).append("\n");
        stringBuilder.append(nonceStr).append("\n");
        stringBuilder.append(timesStamp).append("\n");
        stringBuilder.append(serial_no).append("\n");
//        stringBuilder.append("download_url=" + download_url).append("\n");
        System.out.println("message========");
        System.out.println("结果" + stringBuilder.toString());
        return stringBuilder.toString();
    }





    /**
     * 加签 (prepayId)
     *
     * @param prepayId
     * @return
     */
    public static String getMessageByPrepayId(String prepayId, String appId, Long timeStamp, String random) {
//        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
//        String random = RandomUtil.randomString(32);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(appId).append("\n");
        stringBuilder.append(timeStamp).append("\n");
        stringBuilder.append(random).append("\n");
        stringBuilder.append("prepay_id=" + prepayId).append("\n");
        System.out.println("======message prepayId start ==== ");
        System.out.println(stringBuilder);
        System.out.println("======message prepayId end ==== ");
        return stringBuilder.toString();
    }


    /**
     * 加签 (download_url)
     *
     * @param download_url
     * @param mchid
     * @param nonceStr
     * @param timesStamp
     * @param serial_no
     * @return
     */
    public static String getMessageByDownloadUrl(String download_url,String mchid,String nonceStr,String timesStamp,String serial_no){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mchid).append("\n");
        stringBuilder.append(nonceStr).append("\n");
        stringBuilder.append(timesStamp).append("\n");
        stringBuilder.append(serial_no).append("\n");
        System.out.println("======message prepayId start ==== ");
        System.out.println(stringBuilder);
        System.out.println("======message prepayId end ==== ");
        return stringBuilder.toString();
    }



    /**
     * 调起支付加签(签名)
     * @param prepayId
     * @param payProperties
     * @return
     * @throws IOException
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static Map getSignByPrePayId(String prepayId, PayProperties payProperties) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        String nonceStr = RandomUtil.randomString(32);
        long timestamp = System.currentTimeMillis() / 1000;
        String path = ResourceUtils.getFile("classpath:" + "cert/apiclient_key.pem").getPath();
        if (StringUtils.isEmpty(path)) {
            throw new RuntimeException("文件路径不存在");
        }
        String privateKeyString = FileUtil.getValue(path);
        PrivateKey privateKey = PemUtil.loadPrivateKey(privateKeyString);
        String signature = sign(getMessageByPrepayId(prepayId, payProperties.getAppId(), timestamp, nonceStr).getBytes("utf-8"), privateKey);
        return MapUtil.builder()
                .put("mchid",payProperties.getMchId())
                .put("nonce_str",nonceStr)
                .put("timestamp",timestamp)
                .put("serial_no",payProperties.getMchSerialno())
                .put("signature",signature)
                .map();
    }




    /**
     * 对下载进行加签(签名)
     * @param download_url
     * @param payProperties
     * @return
     * @throws IOException
     * @throws SignatureException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static Map getSignByDownloadUrl(String download_url,PayProperties payProperties) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        String path = ResourceUtils.getFile("classpath:" + "cert/apiclient_key.pem").getPath();
        if (StringUtils.isEmpty(path)) {
            throw new RuntimeException("文件路径不存在");
        }
        String privateKeyString = FileUtil.getValue(path);
        PrivateKey privateKey = PemUtil.loadPrivateKey(privateKeyString);
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String signature = sign(getMessage2(download_url,payProperties.getMchId(),
                payProperties.getMchSerialno(),timeStamp,payProperties.getMchSerialno()).getBytes("utf-8"), privateKey);
        String random = RandomUtil.randomString(32);

        return MapUtil.builder()
                .put("mchid",payProperties.getMchId())
                .put("nonce_str",random)
                .put("timestamp",timeStamp)
                .put("serial_no",payProperties.getMchSerialno())
                .put("signature",signature)
                .map();
    }





    public static void main(String[] args) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        //System.out.println(getToken2("https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx"));
    }

}
