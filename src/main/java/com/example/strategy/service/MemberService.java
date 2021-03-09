package com.example.strategy.service;

import com.example.strategy.enums.MemberLevelEnum;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 15:08
 * @description 测试demo
 */
public interface MemberService {

    MemberLevelEnum getVipLevel(Integer code);
}
