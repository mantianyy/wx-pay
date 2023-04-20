package com.wx.pay.dto.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jiang漫天
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BillExcelDto {
    @ExcelProperty("用户标识")
    @ColumnWidth(10)
    @ApiModelProperty("用户标识")
    private String userId;

    @ExcelProperty("交易类型")
    @ColumnWidth(10)
    @ApiModelProperty("交易类型")
    private String transactionType;

    @ExcelProperty("交易状态")
    @ColumnWidth(10)
    @ApiModelProperty("交易状态")
    private String tradeStatus;

    @ExcelProperty("付款银行")
    @ColumnWidth(10)
    @ApiModelProperty("付款银行")
    private String payBank;

    @ExcelProperty("货币种类")
    @ColumnWidth(10)
    @ApiModelProperty("货币种类")
    private String type;

    @ExcelProperty("应结订单金额")
    @ColumnWidth(10)
    @ApiModelProperty("应结订单金额")
    private String dueOrderAmount;

    @ExcelProperty("代金券金额")
    @ColumnWidth(10)
    @ApiModelProperty("代金券金额")
    private String voucherAmount;

    @ExcelProperty("微信退款单号")
    @ColumnWidth(10)
    @ApiModelProperty("微信退款单号")
    private String wxRefundOrderNo;

    @ExcelProperty("商户退款单号")
    @ColumnWidth(10)
    @ApiModelProperty("商户退款单号")
    private String merchantRefundOrderNol;

    @ExcelProperty("退款金额")
    @ColumnWidth(10)
    @ApiModelProperty("退款金额")
    private String refundAmount;

    @ExcelProperty("充值券退款金额")
    @ColumnWidth(10)
    @ApiModelProperty("充值券退款金额")
    private String rechargeCouponRefundAmount;

    @ExcelProperty("退款类型")
    @ColumnWidth(10)
    @ApiModelProperty("退款类型")
    private String refundType;

    @ExcelProperty("退款状态")
    @ColumnWidth(10)
    @ApiModelProperty("退款状态")
    private String refundStatus;

    @ExcelProperty("商品名称")
    @ColumnWidth(10)
    @ApiModelProperty("商品名称")
    private String productName;

    @ExcelProperty("商户数据包")
    @ColumnWidth(10)
    @ApiModelProperty("商户数据包")
    private String merchantDataPackage;

    @ExcelProperty("手续费")
    @ColumnWidth(10)
    @ApiModelProperty("手续费")
    private String handleFee;

    @ExcelProperty("费率")
    @ColumnWidth(10)
    @ApiModelProperty("费率")
    private String rate;

    @ExcelProperty("订单金额")
    @ColumnWidth(10)
    @ApiModelProperty("订单金额")
    private String orderAmount;

    @ExcelProperty("申请退款金额")
    @ColumnWidth(10)
    @ApiModelProperty("申请退款金额")
    private String getOrderAmount;

    @ExcelProperty("费率备注")
    @ColumnWidth(10)
    @ApiModelProperty("费率备注")
    private String rateRemark;

}
