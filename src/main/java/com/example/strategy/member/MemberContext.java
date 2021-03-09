package com.example.strategy.member;

import com.example.strategy.bean.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 11:11
 * @description 会员等级环境角色
 */
@Component
@Slf4j
public class MemberContext{

    @Autowired
    private List<MemberStrategy> memberStrategies;

    public BigDecimal match(Cart cart){
        Optional<MemberStrategy> optional = memberStrategies.stream()
                .filter(strategy -> strategy.getLevel().equals(cart.getUidLevel()))
                .findFirst();
        AtomicReference<BigDecimal> amount = new AtomicReference(BigDecimal.ZERO);
        optional.ifPresent(op->{
            amount.set(optional.get().exec(cart));
        });
        return amount.get();
    }


}
