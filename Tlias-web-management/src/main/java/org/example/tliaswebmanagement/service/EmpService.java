package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.EmpQueryParam;
import org.example.tliaswebmanagement.pojo.JobOption;
import org.example.tliaswebmanagement.pojo.PageResult;

import java.util.List;
import java.util.Map;

public interface EmpService {

    PageResult<Emp> findEmpData(EmpQueryParam empQueryParam);

    void add(Emp emp);

    void delete(List<Integer> ids);

    void update(Emp emp);

    Emp findbyId(Integer id);

    JobOption getempJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Emp> findAllEmp();
}
