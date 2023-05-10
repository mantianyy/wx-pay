import com.alibaba.fastjson.JSONObject;
import com.wx.dto.notify.NotifiyPayResultDto;
import com.wx.dto.notify.NotifyPayEncryptDto;

public class JunitTest2 {

    public static void main(String[] args) {
        String str = "{\n" +
                "    \"id\": \"EV-2018022511223320873\",\n" +
                "    \"create_time\": \"2015-05-20T13:29:35+08:00\",\n" +
                "    \"resource_type\": \"encrypt-resource\",\n" +
                "    \"event_type\": \"TRANSACTION.SUCCESS\",\n" +
                "    \"summary\": \"支付成功\",\n" +
                "    \"resource\": {\n" +
                "        \"original_type\": \"transaction\",\n" +
                "        \"algorithm\": \"AEAD_AES_256_GCM\",\n" +
                "        \"ciphertext\": \"\",\n" +
                "        \"associated_data\": \"\",\n" +
                "        \"nonce\": \"\"\n" +
                "    }\n" +
                "}";
        NotifyPayEncryptDto notifyPayEncryptDto = JSONObject.parseObject(str, NotifyPayEncryptDto.class);
        System.out.println(notifyPayEncryptDto);
    }
}
