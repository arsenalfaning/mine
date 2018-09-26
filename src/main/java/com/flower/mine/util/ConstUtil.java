package com.flower.mine.util;

/**
 * 全局静态常量
 * @author wanglei,wangleilc@inspur.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConstUtil {

    public static final Byte Delete_Flag_Yes = 1;
    public static final Byte Delete_Flag_No = 0;

    public static final String Cache_Name_Auth_Codes = "admin.auth.codes";

    public static final String Request_Header_Name_Roles_Codes = "X-Roles-Codes";
    public static final String Request_Header_Name_Session = "X-Session";
    public static final String Request_Header_Jwt_Session_Key = "session";

    public static final String Parameter_Hash_Cost = "初期成本参数";
    public static final String Parameter_Hash_Fee = "日常费用参数";
    public static final String Parameter_Hash_Earning = "挖矿收益参数";
    public static final String Parameter_Admin_Address = "接收比特币地址";
    public static final String Parameter_Withdraw_Min = "最小提现数额";
    public static final String Parameter_Recommend = "一次性推荐奖励";
}
