package com.example.strategy.promotion;

import com.example.strategy.bean.CouponInfo;

import java.math.BigDecimal;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-04 11:03
 * @description 抽象优惠劵折扣类型(折扣策略接口)
 */
public interface ICouponDiscountStrategy {

    Integer getCouponType();

    BigDecimal discountAmount(CouponInfo couponInfo);
}
