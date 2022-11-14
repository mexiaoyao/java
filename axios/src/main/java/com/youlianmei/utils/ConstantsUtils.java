package com.youlianmei.utils;

public class ConstantsUtils {

    public static final Byte BYTE_1 = 1; //Byte类型1 一般表示成功

    public static final Byte BYTE_2 = 2; //Byte类型2 一般表示失败

    public static final String INDEXTYPE_1 = "sh";

    public static final String INDEXTYPE_2 = "sz";

    public static final String SHARES_TABLENAME_PRFIX = "t_shares_"; //股票记录表前缀


    public static final String WHERE_EQ = "eq"; //where查询参数统一设置 等于
    public static final String WHERE_GE = "ge"; //where查询参数统一设置 大于等于
    public static final String WHERE_GT = "gt"; //where查询参数统一设置 大于
    public static final String WHERE_LE = "le"; //where查询参数统一设置 小于等于
    public static final String WHERE_LT = "lt"; //where查询参数统一设置 小于
    public static final String WHERE_LIKE = "like"; //where查询参数统一设置 模糊查询
    public static final String WHERE_BETWEEN = "between"; //where查询参数统一设置 模糊查询


    /**
     * 股票类型转为字符
     * @param indexType
     * @return
     */
    public static String indexTypeLetter(Byte indexType){
        switch(indexType){
            case 1 :
                return INDEXTYPE_1;
            case 2:
                return INDEXTYPE_2;
            default:
                return "";
        }
    }

}
