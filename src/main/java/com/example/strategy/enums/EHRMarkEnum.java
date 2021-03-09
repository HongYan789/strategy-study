package com.example.strategy.enums;

/**
 * EHR-API数据库中
 * 用户标识
 *  @author dearzhang
 */
public enum EHRMarkEnum {
    /**
     * 门店人员标识、门店员工方法调用
     */
    STORE("X","门店员工标识","StoreEmployeeMethod"),
    /**
     * 职能人员标识、职能员工方法调用
     */
    FUNCTION("Y","职能员工标识","FunctionEmployeeMethod"),
    /**
     * 全部人员标识、全部员工方法调用
     */
    ALL("ALL","全部用户","AllEmployeeMethod"),

            ;
    private String code;
    private String value;
    private String method;

    EHRMarkEnum(String code, String value, String method){
        this.code = code;
        this.value = value;
        this.method = method;
    }

    public static String getValueByCode(String code){
        EHRMarkEnum[] array = values();
        for(EHRMarkEnum arr: array){
            if(arr.code.equals(code)){
                return arr.value;
            }
        }
        return null;
    }

    public static String getMethodByCode(String code){
        EHRMarkEnum[] array = values();
        for(EHRMarkEnum arr: array){
            if(arr.code.equals(code)){
                return arr.method;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getMethod() {
        return method;
    }

}
