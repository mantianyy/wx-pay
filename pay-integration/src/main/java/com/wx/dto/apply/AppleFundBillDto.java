package com.wx.dto.apply;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("申请资金账单入参模型")
@Data
public class AppleFundBillDto {

    @ApiModelProperty("账单日期")
    @JSONField(name = "bill_date")
    private String billDate;

    @ApiModelProperty("资金账户类型")
    @JSONField(name = "account_type")
    private String accountType;

    @ApiModelProperty("压缩类型")
    @JSONField(name = "tar_type")
    private String tarType;
}
