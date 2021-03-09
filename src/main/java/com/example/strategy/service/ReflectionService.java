package com.example.strategy.service;

import com.example.strategy.bean.Employee;

import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-08 17:39
 * @description 反射实现数据查询
 */
public interface ReflectionService {
    List<Employee> queryTargetUsers(String mark, String jobNo) throws Exception;
}
