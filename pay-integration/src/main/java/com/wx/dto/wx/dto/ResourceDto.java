package com.wx.dto.wx.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jiang漫天
 */
@ApiModel(value = "订单支付通知详情")
@Data
public class ResourceDto {
    @ApiModelProperty("加密算法类型")
    @JSONField(name = "algorithm")
    private String algorithm;

    @ApiModelProperty("数据密文")
    @JSONField(name = "ciphertext")
    private String ciphertext;

    @ApiModelProperty("附加数据")
    @JSONField(name = "associated_data")
    private String associatedData;

    @ApiModelProperty("原始类型")
    @JSONField(name = "original_type")
    private String originalType;

    @ApiModelProperty("随机串")
    @JSONField(name = "nonce")
    private String nonce;
}
