package com.example.strategy.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 10:51
 * @description 商品实体类
 */
@Data
public class Product implements Serializable {
    /**
     * 购物车商品主键
     */
    private Long cartProductId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 上架状态
     */
    private Integer publishStat;
    /**
     * 上架状态名称
     */
    private String publishStatName;
    /**
     * 商品图片
     */
    private String productImg;
    /**
     * 规格名称
     */
    private String specsName;
    /**
     * 销售价
     */
    private BigDecimal salePrice;
    /**
     * 划线价
     */
    private BigDecimal markPrice;

    /**
     * 商品个数
     */
    private int prodCnt;//商品个数

}
