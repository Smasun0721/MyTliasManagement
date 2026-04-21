package org.example.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tliaswebmanagement.mapper.EmpExprMapper;
import org.example.tliaswebmanagement.mapper.EmpLogMapper;
import org.example.tliaswebmanagement.mapper.EmpMapper;
import org.example.tliaswebmanagement.pojo.*;
import org.example.tliaswebmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogMapper empLogMapper;

    //-----------------------------------原始分页查询-------------------------------

//        public PageResult<Emp> findEmpData(Integer page, Integer pageSize) {
//    Integer count = empMapper.findCount();
//    List<Emp> list = empMapper.find((page - 1) * pageSize, pageSize);
//        return new PageResult(count,list);
//    }
//

    //-----------------------------------PageHelper---------------------------------
    public PageResult<Emp> findEmpData(EmpQueryParam empQueryParam) {

        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        List<Emp> emplist = empMapper.find(empQueryParam);

        Page<Emp> p = (Page<Emp>) emplist;
        return new PageResult<Emp>(p.getTotal(), p.getResult());

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void add(Emp emp) {
        try {
            //添加员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.add(emp);
            //添加员工工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if (!exprList.isEmpty()) {
                //为工作经历绑定员工ID
                for (EmpExpr empExpr : exprList) {
                    empExpr.setEmpId(emp.getId());
                }
                empExprMapper.add(emp.getExprList());
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp);
            empLogMapper.insert(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        List<Emp> deleteEmps = empMapper.findByIds(ids);
        for (Emp deleteEmp : deleteEmps) {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "删除员工" + deleteEmp);
            empLogMapper.insert(empLog);
        }
        empMapper.delete(ids);
        empExprMapper.delete(ids);
    }

    @Override
    public Emp findbyId(Integer id) {
        Emp emp = empMapper.findById(id);
        List<EmpExpr> empExprs = empExprMapper.findById(id);
        emp.setExprList(empExprs);
        return emp;
    }

    @Override
    public JobOption getempJobData() {
        List<Map<String, Object>> list = empMapper.getEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("number")).toList();
        JobOption jobOption = new JobOption(jobList, dataList);
        return jobOption;
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        List<Map<String, Object>> empGenderDataList = empMapper.getEmpGenderData();
        return empGenderDataList;
    }

    @Override
    public List<Emp> findAllEmp() {
        List<Emp> allEmp = empMapper.findAllEmp();
        return allEmp;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        try {
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.update(emp);      //修改员工基本信息
            List<EmpExpr> exprList = emp.getExprList();     //获取修改后员工的工作经历
            empExprMapper.deleteById(emp.getId());      //删除员工已存在的工作经历
            if (!exprList.isEmpty()) {
                //为工作经历绑定员工ID
                for (EmpExpr empExpr : exprList) {
                    empExpr.setEmpId(emp.getId());
                }
                empExprMapper.add(exprList);//添加修改后员工的工作经历
            }
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "修改后员工" + emp);
            empLogMapper.insert(empLog);
        }
    }
}

