package com.wx.dto.apply;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jiang漫天
 */
@ApiModel("申请交易账单出参模型")
@Data
public class ApplyTradeBillResDto {
    @ApiModelProperty("哈希类型")
    @JSONField(name = "hash_type")
    private String hashType;
    @ApiModelProperty("哈希值")
    @JSONField(name = "hash_value")
    private String hashValue;
    @ApiModelProperty("账单下载地址")
    @JSONField(name = "download_url")
    private String downloadUrl;
}
