package org.example.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    void add(List<EmpExpr> exprList);

    void delete(List<Integer> EmpIds);

    @Select("select * from emp_expr where emp_id=#{id}")
    List<EmpExpr> findById(Integer id);

    @Delete("delete from emp_expr where emp_id=#{id}")
    void deleteById(Integer id);
}
