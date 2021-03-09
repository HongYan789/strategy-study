package com.example.strategy.controller;

import com.example.strategy.service.ReflectionService;
import com.example.strategy.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zy
 * @date Created in 2021-03-09 09:46
 * @description 采用反射动态实现方法调用
 */
@RequestMapping("/reflection")
@RestController
@Slf4j
public class ReflectionController {

    @Autowired
    private ReflectionService reflectionService;

    @GetMapping("/getData")
    public R getData(String mark, String jobNo) throws Exception {
        return R.sucess(reflectionService.queryTargetUsers(mark,jobNo));
    }

}
