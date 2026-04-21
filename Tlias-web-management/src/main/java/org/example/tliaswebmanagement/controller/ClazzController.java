package org.example.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.exception.custom.ClassNotNull;
import org.example.tliaswebmanagement.pojo.Clazz;
import org.example.tliaswebmanagement.pojo.ClazzQueryParam;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping   //分页查询班级
    public Result findAllClazz(ClazzQueryParam clazzQueryParam) {
        log.info("分页查询:{}", clazzQueryParam);

        PageResult<Clazz> clazzPageResult = clazzService.findAllClazz(clazzQueryParam);
        return Result.success(clazzPageResult);
    }

    @DeleteMapping("/{id}")     //根据ID删除指定班级
    public Result deleteById(@PathVariable Integer id) throws ClassNotNull {
        log.info("正在删除ID为{}的班级", id);

        clazzService.deleteById(id);
        return Result.success();
    }

    @PostMapping    //添加班级
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级:{}", clazz);

        clazzService.add(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")    //根据ID查询指定班级(回显)
    public Result findById(@PathVariable Integer id) {
        log.info("根据ID查询指定班级(回显):{}", id);

        Clazz clazzData = clazzService.findById(id);
        return Result.success(clazzData);
    }

    @PutMapping     //修改班级数据
    public Result update(@RequestBody Clazz clazzData) {
    log.info("修改班级数据:{}",clazzData);

    clazzService.update(clazzData);
    return Result.success();
    }

    @GetMapping("/list")    //查询所有班级(下拉列表数据)
    public Result findAll(){
        log.info("查询所有班级");

        List<Clazz> allClazz = clazzService.findAll();
        return Result.success(allClazz);
    }

}
