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
 * @description 直减折扣优惠（直降N圆，直接减N圆）
 * 直减计算
 * 1. 使用商品价格减去优惠价格
 * 2. 最低支付金额1元
 */
@Component
@Slf4j
public class ZJCouponDiscount implements ICouponDiscountStrategy {
    @Override
    public Integer getCouponType() {
        return CouponEnum.ZJ.getCode();
    }

    /***
     *
     * @author zy
     * @date 2021-03-05 17:29
     * @description
     * 直减计算
     * 1. 使用商品价格减去优惠价格
     * 2. 最低支付金额1元
     * @return java.math.BigDecimal
     */
    @Override
    public BigDecimal discountAmount(CouponInfo couponInfo) {
        BigDecimal discountAmt = couponInfo.getPaidAmt().subtract(couponInfo.getFallAmt());
        if(discountAmt.compareTo(BigDecimal.ZERO)< 1){
            return BigDecimal.ONE;
        }
        return discountAmt;
    }
}
