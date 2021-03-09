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
 * @description N圆购折扣优惠（无论原价多少钱都固定金额购买）
 * n元购购买
 * 1. 无论原价多少钱都固定金额购买
 */
@Component
@Slf4j
public class NYCouponDiscount implements ICouponDiscountStrategy {
    @Override
    public Integer getCouponType() {
        return CouponEnum.NY.getCode();
    }

    /***
     *
     * @author zy
     * @date 2021-03-05 17:29
     * @description
     * n元购购买
     * 1. 无论原价多少钱都固定金额购买
     * @return java.math.BigDecimal
     */
    @Override
    public BigDecimal discountAmount(CouponInfo couponInfo) {
        return couponInfo.getNyAmt();
    }
}
