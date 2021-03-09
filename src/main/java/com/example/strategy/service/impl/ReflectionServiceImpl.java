package com.example.strategy.service.impl;

import com.example.strategy.bean.Employee;
import com.example.strategy.enums.EHRMarkEnum;
import com.example.strategy.mapper.EmployeeMapper;
import com.example.strategy.service.ReflectionService;
import com.example.strategy.utils.OpCode;
import com.example.strategy.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zy
 * @version 1.0
 * @date Created in 2021-03-08 17:39
 * @description 反射实现数据查询
 */
@Service
@Slf4j
public class ReflectionServiceImpl implements ReflectionService{

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询出目标人群
     * @return
     */
    @Override
    public List<Employee> queryTargetUsers(String mark, String jobNo) throws Exception {
        if(StringUtils.isEmpty(jobNo)){
            jobNo = "";
        }
        String method = EHRMarkEnum.getMethodByCode(mark);
        if(StringUtils.isEmpty(method)){
            log.error("通过人员标识：[{}]查询出目标人群失败",mark);
            return new ArrayList<>();
        }
        Object[] args=new Object[]{jobNo};
        R result = (R)invokeMethod(method,args);

        if(OpCode.Success == result.getCode()){
            List<Employee> list = (List<Employee>)result.getData();
            log.info("查询出目标人群返回结果：[{}] count",list.size());
            return list;
        }
        return new ArrayList<>();
    }


    /***
     * 根据方法名进行动态调用方法
     * @param methodName
     * @param args
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object invokeMethod(String methodName ,Object[] args) throws Exception {
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = this.getClass().getMethod(methodName,argsClass);
        return method.invoke(this, args);
    }

    /**
     * 门店员工集合
     * 调用ehr-open中查询门店员工接口
     * @return
     */
    public R StoreEmployeeMethod(String jobNo){
        try{
            List<Employee> list = employeeMapper.getStorePersonList();
            return new R(OpCode.Success,"调用ehr-open中查询门店员工成功",list);
        }catch (Exception e){
            log.error("调用ehr-open中查询门店员工接口异常",e);
            return new R(OpCode.Internal,"调用ehr-open中查询门店员工接口异常",null);
        }
    }

    /**
     * 职能员工集合
     * @return
     */
    public R FunctionEmployeeMethod(String jobNo){
        try{
            List<Employee> list = employeeMapper.getFunctionPersonList();
            return new R(OpCode.Success,"调用ehr-open中查询职能员工成功",list);
        }catch (Exception e){
            log.error("调用ehr-open中查询职能员工接口异常",e);
            return new R(OpCode.Internal,"调用ehr-open中查询职能员工接口异常",null);
        }
    }

    /**
     * 全部在职员工集合
     * @return
     */
    public R AllEmployeeMethod(String jobNo){
        try{
            List<String> jobNoList = new ArrayList<>();
            String[] array = jobNo.split(",");
            for(String no : array){
                if(!StringUtils.isEmpty(no)){
                    jobNoList.add(no);
                }
            }

            List<Employee> list = employeeMapper.getAllPersonList(jobNoList);
            return new R(OpCode.Success,"调用ehr-open中查询所有在职员工成功",list);
        }catch (Exception e){
            log.error("调用ehr-open中查询所有在职员工接口异常",e);
            return new R(OpCode.Internal,"调用ehr-open中查询所有在职员工接口异常",null);
        }
    }
}
