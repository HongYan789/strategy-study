package com.example.strategy.member;

import com.example.strategy.bean.Cart;
import java.math.BigDecimal;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 10:49
 * @description 会员等级策略模式
 */
public interface MemberStrategy {

    Integer getLevel();

    /**
     * 通过购物车中会员等级uidLevel计算购物车优惠
     * @param cart
     * @return
     */
    BigDecimal exec(Cart cart);
}
