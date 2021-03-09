package com.example.strategy.mapper;

import com.example.strategy.bean.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface EmployeeMapper {


    @Select({"SELECT " +
            "    e.pk_id as pkId, e.name as name, e.job_no as jobNo, e.sap_state as sapState,  " +
            "    e.sap_department_id as sap_departmentId, e.mobile as mobile, e.email as email, " +
            "    e.fk_ding_id as fkDingId, e.station_id as stationId, e.sub_dep_id as subDepId,  " +
            "    e.parent_id as parentId,d.name as store,e.wechat_user_id as wechatUserId " +
            "FROM " +
            "    t_employee e, " +
            "    t_department d " +
            "WHERE " +
            "    e.sap_department_id = d.fk_sap_id " +
            "    AND d.ob_type = 'O' " +
            "    AND d.store_bs = 'X' " +
            "    AND d.sap_state in (10,20) " +
            "    AND e.sap_state = 3  order by e.update_time desc "})
    List<Employee> getStorePersonList();


    @Select({"SELECT " +
            "    e.pk_id as pkId, e.name as name, e.job_no as jobNo, e.sap_state as sapState,  " +
            "    e.sap_department_id as sap_departmentId, e.mobile as mobile, e.email as email, " +
            "    e.fk_ding_id as fkDingId, e.station_id as stationId, e.sub_dep_id as subDepId,  " +
            "    e.parent_id as parentId,d.name as store,e.wechat_user_id as wechatUserId " +
            "FROM " +
            "    t_employee e, " +
            "    t_department d " +
            "WHERE " +
            "    e.sap_department_id = d.fk_sap_id " +
            "    AND d.ob_type = 'O' " +
            "    AND d.store_bs != 'X' " +
            "    AND d.sap_state in (10,20) " +
            "    AND e.sap_state = 3 order by e.update_time desc "})
    List<Employee> getFunctionPersonList();


    @Select({"<script> SELECT " +
            "    e.pk_id as pkId, e.name as name, e.job_no as jobNo, e.sap_state as sapState,  " +
            "    e.sap_department_id as sapDepartmentId, e.mobile as mobile, e.email as email, " +
            "    e.fk_ding_id as fkDingId, e.station_id as stationId, e.sub_dep_id as subDepId,  " +
            "    d.parent_sap_id as parentId,d.name as store,e.wechat_user_id as wechatUserId " +
            "FROM " +
            "    t_employee e, " +
            "    t_department d " +
            "WHERE " +
            "    e.sap_department_id = d.fk_sap_id " +
            "    AND d.ob_type = 'O' " +
            "    AND d.sap_state in (10,20) " +
            "    AND e.sap_state = 3 " +
            " <if test='list!=null and list.size()>0'> "+
            " and e.job_no in "+
            " <foreach collection='list' item='list' open='(' separator=',' close=')' > "+
            " #{list} "+
            " </foreach> "+
            " </if> "+
            " order by e.update_time desc" +
            " </script> "})
    List<Employee> getAllPersonList(@Param("list") List<String> list);



}
