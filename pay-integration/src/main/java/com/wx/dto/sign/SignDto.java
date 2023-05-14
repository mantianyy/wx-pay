package com.wx.dto.sign;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.wx.common.SignTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "签名生成参数")
public class SignDto {

    @ApiModelProperty(value = "")
    @JsonEnumDefaultValue
    @JSONField(name = "sign_type")
    private SignTypeEnum signType;
    @ApiModelProperty(value = "加签值")
    @JSONField(name = "value")
    private String value;
}
