package com.example.strategy.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-05 16:47
 * @description 优惠信息
 */
@Data
public class CouponInfo implements Serializable {

    private BigDecimal paidAmt;//应付金额

    private BigDecimal sillAmt;//门槛金额

    private BigDecimal fullReductionAmt;//满减金额

    private BigDecimal discount;//折扣

    private BigDecimal nyAmt;//n圆购金额

    private BigDecimal fallAmt;//直降金额

    private Integer type;//促销类型，对应CouponEnum枚举值
}
