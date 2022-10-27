package com.youlianmei.utils;

public class ConstantsUtils {

    public static final Byte BYTE_1 = 1; //Byte类型1 一般表示成功

    public static final Byte BYTE_2 = 2; //Byte类型2 一般表示失败

    public static final String INDEXTYPE_1 = "sh";

    public static final String INDEXTYPE_2 = "sz";

    public static final String SHARES_TABLENAME_PRFIX = "t_shares_"; //股票记录表前缀

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
