package com.wx.dto.wx.dto.apply_tradebill;

import com.alibaba.fastjson.annotation.JSONField;
import com.wx.common.TarTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jiang漫天
 */
@ApiModel("申请交易账单/申请资金账单模型")
@Data
public class ApplyTradeDto {
    @ApiModelProperty("账单日期")
    @JSONField(name = "bill_date")
    private String billDate;
    @ApiModelProperty("账单类型")
    @JSONField(name = "bill_type")
    private String billType;

    @ApiModelProperty("资金账户类型")
    @JSONField(name = "account_type")
    private String accountType;

    @ApiModelProperty("压缩类型")
    @JSONField(name = "tar_type")
    private TarTypeEnum tarType;

}
