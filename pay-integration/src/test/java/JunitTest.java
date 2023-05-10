import com.alibaba.fastjson.JSONObject;
import com.wx.dto.notify.NotifiyPayResultDto;

public class JunitTest {

    public static void main(String[] args) {
        String str = "{\n" +
                "    \"transaction_id\":\"1217752501201407033233368018\",\n" +
                "    \"amount\":{\n" +
                "        \"payer_total\":100,\n" +
                "        \"total\":100,\n" +
                "        \"currency\":\"CNY\",\n" +
                "        \"payer_currency\":\"CNY\"\n" +
                "    },\n" +
                "    \"mchid\":\"1230000109\",\n" +
                "    \"trade_state\":\"SUCCESS\",\n" +
                "    \"bank_type\":\"CMC\",\n" +
                "    \"promotion_detail\":[\n" +
                "        {\n" +
                "            \"amount\":100,\n" +
                "            \"wechatpay_contribute\":0,\n" +
                "            \"coupon_id\":\"109519\",\n" +
                "            \"scope\":\"GLOBAL\",\n" +
                "            \"merchant_contribute\":0,\n" +
                "            \"name\":\"单品惠-6\",\n" +
                "            \"other_contribute\":0,\n" +
                "            \"currency\":\"CNY\",\n" +
                "            \"stock_id\":\"931386\",\n" +
                "            \"goods_detail\":[\n" +
                "                {\n" +
                "                    \"goods_remark\":\"商品备注信息\",\n" +
                "                    \"quantity\":1,\n" +
                "                    \"discount_amount\":1,\n" +
                "                    \"goods_id\":\"M1006\",\n" +
                "                    \"unit_price\":100\n" +
                "                },\n" +
                "                {\n" +
                "                    \"goods_remark\":\"商品备注信息\",\n" +
                "                    \"quantity\":1,\n" +
                "                    \"discount_amount\":1,\n" +
                "                    \"goods_id\":\"M1006\",\n" +
                "                    \"unit_price\":100\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"amount\":100,\n" +
                "            \"wechatpay_contribute\":0,\n" +
                "            \"coupon_id\":\"109519\",\n" +
                "            \"scope\":\"GLOBAL\",\n" +
                "            \"merchant_contribute\":0,\n" +
                "            \"name\":\"单品惠-6\",\n" +
                "            \"other_contribute\":0,\n" +
                "            \"currency\":\"CNY\",\n" +
                "            \"stock_id\":\"931386\",\n" +
                "            \"goods_detail\":[\n" +
                "                {\n" +
                "                    \"goods_remark\":\"商品备注信息\",\n" +
                "                    \"quantity\":1,\n" +
                "                    \"discount_amount\":1,\n" +
                "                    \"goods_id\":\"M1006\",\n" +
                "                    \"unit_price\":100\n" +
                "                },\n" +
                "                {\n" +
                "                    \"goods_remark\":\"商品备注信息\",\n" +
                "                    \"quantity\":1,\n" +
                "                    \"discount_amount\":1,\n" +
                "                    \"goods_id\":\"M1006\",\n" +
                "                    \"unit_price\":100\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"success_time\":\"2018-06-08T10:34:56+08:00\",\n" +
                "    \"payer\":{\n" +
                "        \"openid\":\"oUpF8uMuAJO_M2pxb1Q9zNjWeS6o\"\n" +
                "    },\n" +
                "    \"out_trade_no\":\"1217752501201407033233368018\",\n" +
                "    \"appid\":\"wxd678efh567hg6787\",\n" +
                "    \"trade_state_desc\":\"支付成功\",\n" +
                "    \"trade_type\":\"MICROPAY\",\n" +
                "    \"attach\":\"自定义数据\",\n" +
                "    \"scene_info\":{\n" +
                "        \"device_id\":\"013467007045764\"\n" +
                "    }\n" +
                "}";

        NotifiyPayResultDto notifiyPayResultDto = JSONObject.parseObject(str, NotifiyPayResultDto.class);
        System.out.println(notifiyPayResultDto);
    }
}
