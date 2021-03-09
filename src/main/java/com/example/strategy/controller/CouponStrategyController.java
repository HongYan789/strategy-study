package com.example.strategy.controller;

import com.example.strategy.bean.Cart;
import com.example.strategy.bean.CartRequest;
import com.example.strategy.bean.CouponInfo;
import com.example.strategy.member.MemberContext;
import com.example.strategy.promotion.CouponDiscountContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 15:55
 * @description 模拟在购买商品时候使用的各种类型优惠券(满减、直减、折扣、n元购)
 */
@RestController
@RequestMapping("/coupon")
@Slf4j
public class CouponStrategyController {

    @Autowired
    private CouponDiscountContext couponDiscountContext;

    @PostMapping("/getCouponAmt")
    public Object getCouponAmt(@RequestBody CouponInfo couponInfo){
        return couponDiscountContext.match(couponInfo);
    }
}
