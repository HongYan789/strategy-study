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
 * @description 满折折扣优惠（每满X圆打N折，使用商品价格乘以折扣比例，为最后支付金额）
 * 折扣计算
 * 1. 使用商品价格乘以折扣比例，为最后支付金额
 * 2. 保留两位小数
 * 3. 最低支付金额1元
 */
@Component
@Slf4j
public class MZCouponDiscount implements ICouponDiscountStrategy {
    @Override
    public Integer getCouponType() {
        return CouponEnum.MZ.getCode();
    }

    /***
     * 
     * @author zy
     * @date 2021-03-05 17:29
     * @description
     * 满折优惠
     * 1. 使用商品价格乘以折扣比例，为最后支付金额
     * 2. 保留两位小数
     * 3. 最低支付金额1元
     * @return java.math.BigDecimal
     */
    @Override
    public BigDecimal discountAmount(CouponInfo couponInfo) {
        //优惠金额 = 应付金额*折扣系数后取小数点后2位四舍五入结果
        BigDecimal discountAmt = couponInfo.getPaidAmt().multiply(couponInfo.getDiscount()).setScale(2,BigDecimal.ROUND_HALF_UP);
        if(discountAmt.compareTo(BigDecimal.ZERO) < 1){
            return BigDecimal.ONE;
        }
        return discountAmt;
    }
}
