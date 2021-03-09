package com.example.strategy.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 15:56
 * @description 购物车rqeust入参
 */
@Data
public class CartRequest implements Serializable {

    private Integer uidLevel;
    private BigDecimal totalAmt;
}
