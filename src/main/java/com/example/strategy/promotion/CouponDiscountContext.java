package com.example.strategy.promotion;

import com.example.strategy.bean.CouponInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-04 11:00
 * @description 促销优惠环境角色，提供统一调用入口
 *
 * 实现促销优惠券折扣计算
 *  优惠券类型；
 *  1. 直减券
 *  2. 满减券
 *  3. 折扣券
 *  4. n元购
 *
 */
@Component
@Slf4j
public class CouponDiscountContext {

    /**
     * 策略模式核心代码在这里，通过@Autowired注入List<interface>来实现动态调用各类具体优惠折扣服务
     * 如果是只需要计算某一种优惠策略服务，则通过type匹配即可
     * 如果需要按照一二三档优惠服务来操作，则可以透过treeSet来将提前定义好的服务依次按顺序调用即可
     */

    @Autowired
    private List<ICouponDiscountStrategy> couponDiscountStrategies;

    public BigDecimal match(CouponInfo couponInfo){
//        //实现按照type值进行排序
//        couponDiscountStrategies = couponDiscountStrategies.stream()
//                .sorted(Comparator.comparing(ICouponDiscountStrategy::getCouponType))
//                .collect(Collectors.toList());
//        //如果需要依次调用一、二、三、默认档促销活动，则可进行如下判断
//        couponDiscountStrategies.stream().map(strategie -> strategie.discountAmount(couponInfo));


        //如果只需要计算对应类型的促销优惠，则取findFirst后执行即可
        Optional<ICouponDiscountStrategy> optional = couponDiscountStrategies.stream()
                .filter(strategy -> strategy.getCouponType().intValue() == couponInfo.getType())
                .findFirst();
        AtomicReference<BigDecimal> amount = new AtomicReference(BigDecimal.ZERO);
        optional.ifPresent(op->{
            amount.set(optional.get().discountAmount(couponInfo));
        });
        return amount.get();
    }

}
