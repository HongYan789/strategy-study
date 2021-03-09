package com.example.strategy.enums;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 10:43
 * @description 会员等级枚举
 */
public enum MemberLevelEnum {
    VIP0(0),
    VIP1(1),
    VIP2(2),
    ;
    private Integer code;

    MemberLevelEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static MemberLevelEnum valueOf(Integer code){
        MemberLevelEnum[] enums = values();
        for (MemberLevelEnum memberLevelEnum: enums){
            if(memberLevelEnum.getCode().intValue() == code){
                return memberLevelEnum;
            }
        }
        return null;
    }
}
