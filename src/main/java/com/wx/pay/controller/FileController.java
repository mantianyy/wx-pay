package com.wx.pay.controller;

import cn.hutool.core.util.RandomUtil;
import com.wx.pay.common.R;
import com.wx.pay.prop.PayProperties;
import com.wx.pay.util.RSAUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

@ApiOperation("文件下载")
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private PayProperties payProperties;
    @Resource
    private HttpClient httpClient;

    @ApiOperation(value = "下载账单")
    @PostMapping("/result/downloadBill")
    public Map downloadBill(String downloadUrl) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        if(StringUtils.isEmpty(downloadUrl)){
            return R.error("下载地址为空");
        }
        HttpGet httpGet2 = new HttpGet(downloadUrl);
        httpGet2.setHeader("Authorization","WECHATPAY2-SHA256-RSA2048 mchid="+payProperties.getMchId());
        httpGet2.setHeader("nonce_str",payProperties.getMchSerialno());
        String nonceStr = RandomUtil.randomString(32);
       // httpGet2.setHeader("signature", RSAUtil.getToken2(downloadUrl,nonceStr));
        //请求下载地址
        CloseableHttpResponse response=null;
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpGet2);
            String fileName = response.getHeaders("Content-Disposition")[0].getValue().split("filename=")[1];
            log.info("文件名为" + fileName);

            if (response.getStatusLine().getStatusCode() == 200) {
                //得到实体
                HttpEntity entity3 = response.getEntity();
                byte[] data = EntityUtils.toByteArray(entity3);
                //存入磁盘
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(data);
                fos.close();
                log.info("文件下载成功！");
            } else {
                return R.ok("文件下载失败！Http状态码为" + response.getStatusLine().getStatusCode(),null);
            }
        } catch (Exception e) {
            log.info("文件下载失败 {}", e.getLocalizedMessage());
            return R.error("文件下载失败");
        } finally {
            response.close();
        }
        return R.ok("下载成功",null);
    }
}
