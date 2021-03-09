package com.example.strategy.member;

import com.example.strategy.bean.Cart;
import com.example.strategy.enums.MemberLevelEnum;
import com.example.strategy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 11:06
 * @description vip2优惠计算
 */
@Component
public class Vip2ConcreteStrategy implements MemberStrategy{

    @Autowired
    private MemberService demoService;

    @Override
    public Integer getLevel() {
        return MemberLevelEnum.VIP2.getCode();
    }

    @Override
    public BigDecimal exec(Cart cart) {
        MemberLevelEnum memberLevelEnum = demoService.getVipLevel(cart.getUidLevel());
        return cart.getTotalAmt().subtract(new BigDecimal(memberLevelEnum.getCode()));
    }


}
