package com.example.strategy.controller;

import com.example.strategy.bean.Cart;
import com.example.strategy.bean.CartRequest;
import com.example.strategy.member.MemberContext;
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
 * @description 购物车服务
 */
@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private MemberContext memberContext;

    @PostMapping("/getCart")
    public Object getCart(@RequestBody CartRequest cartRequest){
        Cart cart = new Cart();
        cart.setUidLevel(cartRequest.getUidLevel());
        cart.setTotalAmt(cartRequest.getTotalAmt());
        return memberContext.match(cart);
    }
}
