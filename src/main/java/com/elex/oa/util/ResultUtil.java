package com.elex.oa.util;

/**
 *@author hugo.zhao
 *@since 2017/12/27 9:39
*/
public enum ResultUtil {

     SC_OK("200","成功"),
     SC_VERFI_FAILED("230","验证失败"),
     SC_HAS_EXIST("220","已经存在"),
     SC_UNKNOWN_ERROR("101","未知异常"),
     SC_INVLID_FUNCTION("105","方法名不存在"),
     SC_NETWORK_ERROR("103","网络异常");

   private  String msg;

   private String code;

    ResultUtil(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
