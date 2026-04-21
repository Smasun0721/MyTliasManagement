package org.example.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.anno.Log;
import org.example.tliaswebmanagement.pojo.Dept;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    public Result findAll() {
        log.info("查询全部部门...");
        return Result.success(deptService.findAll());
    }

    @Log
    @DeleteMapping("/depts")
    public Result deleteById(@RequestParam Integer id) {
        log.info("正在删除id为{}的部门...",id);
        deptService.deleteById(id);
        log.info("已删除id为{}的部门!",id);
        return Result.success();
    }

    @Log
    @PostMapping("/depts")
    public Result Insert(@RequestBody Dept dept) {
        log.info("正在添加新部门{}...",dept);
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        log.info("正在查找id为{}的部门...",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("正在修改部门数据...");
        deptService.update(dept);
        log.info("修改成功!");
        return Result.success();
    }


}
