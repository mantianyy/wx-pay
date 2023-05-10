package com.wx.common;

/**
 * @author jiang漫天
 */
public enum TarTypeEnum {
    GZIP("gzip", "GZIP压缩包"),
    XLSX("xlsx", "xlsx"),
    CSV("csv", "xlsx");

    private String key;
    private String value;

    // 构造方法
    TarTypeEnum(String key, String value) {
        this.key = key;
        this.key = key;
    }


    public static void main(String[] args) {
        System.out.println(TarTypeEnum.GZIP);
    }
}
