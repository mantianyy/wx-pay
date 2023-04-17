package com.wx.pay;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.wx.pay.common.CurrencyEnum;
import com.wx.pay.dto.AmountDto;
import com.wx.pay.dto.CreateOrderDto;
import com.wx.pay.dto.PayerDto;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class StringTest {


    /**
     * 1. 微信下单: POST('https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi')
     * 2. 小程序调起支付API: wx.requestPayment({...})
     * 3. 服务端接收支付结果通知: POST('域名/api/pay/result/notify')
     * 4. 查询订单: GET('https://api.mch.weixin.qq.com/v3/pay/transactions/id/{transaction_id}')
     * 5. 关闭订单: GET('https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}/close')
     * 6. 申请交易账单: GET('https://api.mch.weixin.qq.com/v3/bill/tradebill')
     * 7. 下载账单: GET('通过申请账单接口获取到“download_url”，URL有效期30s')
     * 8. 申请退款: POST('https://api.mch.weixin.qq.com/v3/refund/domestic/refunds')
     * 9. 单笔退款通知: POST('域名/api/pay/refund/notify')
     * 10.查询单笔退款: GET('https://api.mch.weixin.qq.com/v3/refund/domestic/refunds/{out_refund_no}')
     */
    @Test
    public void testJsonCreate() throws IOException {



        int length = "1217752501201407033233368018".length();
        System.out.println(length);

        String path = "D:\\dev_install\\wxcert\\WXCertUtil\\cert\\apiclient_key.pem";
        System.out.println(getPrivateKey2(path));
    }


    public static PrivateKey getPrivateKey(String filename) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filename)), "utf-8");
        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }

    public static PrivateKey getPrivateKey2(String filename) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filename)), "utf-8");
        try {
            String privateKey = content.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的密钥格式");
        }
    }

}
