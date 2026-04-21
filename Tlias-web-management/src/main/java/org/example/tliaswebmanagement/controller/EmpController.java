package org.example.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.EmpQueryParam;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result findPage(EmpQueryParam empQueryParam) {
        log.info("分页查询:{}", empQueryParam);

        PageResult<Emp> pr = empService.findEmpData(empQueryParam);
        return Result.success(pr);
    }

    @PostMapping
    public Result add(@RequestBody Emp emp){
        log.info("新增员工:{}",emp);

        empService.add(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工:{}",ids);

        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        Emp emp=empService.findbyId(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工:{}",emp);

        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")    //查询员工列表(班主任列表)
    public Result findAllEmp(){
        log.info("查询所有员工并组成列表");

        List<Emp> allEmp = empService.findAllEmp();
        return Result.success(allEmp);
    }

}
