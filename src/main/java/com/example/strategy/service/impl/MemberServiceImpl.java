package com.example.strategy.service.impl;

import com.example.strategy.enums.MemberLevelEnum;
import com.example.strategy.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-03 15:08
 * @description 测试demo
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Override
    public MemberLevelEnum getVipLevel(Integer code){
        return MemberLevelEnum.valueOf(code);
    }
}
