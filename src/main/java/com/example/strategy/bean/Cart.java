package com.example.strategy.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 10:50
 * @description 购物车实体类
 */
@Data
public class Cart implements Serializable {

    /**
     * 消费者唯一id,作为主键使用
     */
    private String uid;//消费者唯一id memberid
    /**
     * 会员等级
     */
    private Integer uidLevel;//会员等级
    /**
     * 商品集合
     */
    private List<Product> products;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date crTime;//创建时间
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updTime;//更新时间

    /**
     * 总优惠金额
     */
    private BigDecimal totalSaveAmt = BigDecimal.ZERO;//总优惠金额，单位是元
    /**
     * 购物车总商品个数
     */
    private int prodCnt;//购物车总商品个数

    /**
     * 商品总应付金额（不含运费）
     */
    private BigDecimal totalAmt = BigDecimal.ZERO;//总应付金额
    /**
     * 总实付金额
     */
    private BigDecimal payAmt = BigDecimal.ZERO;//总实付金额

}
