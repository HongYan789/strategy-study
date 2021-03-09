package com.example.strategy.enums;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 10:43
 * @description 优惠劵枚举
 *  优惠券类型；
 *  1. 直减券
 *  2. 满减券
 *  3. 折扣券
 *  4. n元购
 */
public enum CouponEnum {
    MJ(1),//满减
    MZ(2),//满折
    NY(3),//n圆购
    ZJ(0),//直减/直降
    ;
    private Integer code;

    CouponEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static CouponEnum valueOf(Integer code){
        CouponEnum[] enums = values();
        for (CouponEnum memberLevelEnum: enums){
            if(memberLevelEnum.getCode().intValue() == code){
                return memberLevelEnum;
            }
        }
        return null;
    }
}
