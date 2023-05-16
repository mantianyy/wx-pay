package com.wx.dto.apply;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jiang漫天
 */
@ApiModel("申请交易账单入参模型")
@Data
public class ApplyTradeBillDto {
    @ApiModelProperty("账单日期")
    @JSONField(name = "bill_date")
    private String billDate;
    @ApiModelProperty("账单类型")
    @JSONField(name = "bill_type")
    private String billType;
    @ApiModelProperty("压缩类型")
    @JSONField(name = "tar_type")
    private String tarType;
}
