package com.wx.dto.bill;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "资金账单下载/交易账单下载申请 参数模型")
public class BillDto {

    @ApiModelProperty("下载账单链接")
    @JSONField(name = "download_url")
    private String downloadUrl;
}
