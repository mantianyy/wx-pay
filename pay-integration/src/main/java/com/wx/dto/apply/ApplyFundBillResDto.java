package com.wx.dto.apply;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("申请资金账单入参模型")
@Data
public class ApplyFundBillResDto {

    @ApiModelProperty("账单日期")
    @JSONField(name = "hash_type")
    private String hashType;

    @ApiModelProperty("账单日期")
    @JSONField(name = "hash_value")
    private String hashValue;

    @ApiModelProperty("账单日期")
    @JSONField(name = "download_url")
    private String downloadUrl;
}
