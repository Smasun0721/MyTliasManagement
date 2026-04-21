package org.example.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.tliaswebmanagement.pojo.OperateLog;

import java.util.List;

@Mapper
public interface LogMapper {
    @Select("select *,emp.name as operateEmpName from operate_log,emp where emp.id=operate_emp_id order by operate_time desc")
    List<OperateLog> findAll();
}
