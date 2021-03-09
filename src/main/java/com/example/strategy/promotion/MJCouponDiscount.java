package com.example.strategy.promotion;

import com.example.strategy.bean.CouponInfo;
import com.example.strategy.enums.CouponEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-05 09:48
 * @description 满减折扣优惠（每满X圆-Y圆折扣）
 * 满减计算
 * 1. 判断满足x元后-n元，否则不减
 * 2. 最低支付金额1元
 */
@Component
@Slf4j
public class MJCouponDiscount implements ICouponDiscountStrategy {
    @Override
    public Integer getCouponType() {
        return CouponEnum.MJ.getCode();
    }

    /***
     *
     * @author zy
     * @date 2021-03-05 17:28
     * @description
     * 满减计算
     * 1. 判断满足x元后-n元，否则不减
     * 2. 最低支付金额1元
     * @return java.math.BigDecimal
     */
    @Override
    public BigDecimal discountAmount(CouponInfo couponInfo) {
        // 小于商品金额条件的，直接返回商品原价(应付金额<门槛金额)
        if(couponInfo.getPaidAmt().compareTo(couponInfo.getSillAmt()) <0){
            return couponInfo.getPaidAmt();
        }
        // 减去优惠金额判断
        BigDecimal discountAmt = couponInfo.getPaidAmt().subtract(couponInfo.getFullReductionAmt());
        if(discountAmt.compareTo(BigDecimal.ZERO)<1){
            return BigDecimal.ONE;
        }
        return discountAmt;
    }
}
