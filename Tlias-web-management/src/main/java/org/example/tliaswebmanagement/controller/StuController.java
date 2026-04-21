package org.example.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.pojo.StuQueryParam;
import org.example.tliaswebmanagement.pojo.Student;
import org.example.tliaswebmanagement.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StuController {

    @Autowired
    private StuService stuService;

    @GetMapping     //查询学员列表
    public Result findAllStu(StuQueryParam stuQueryParam) {
        log.info("查询所有的学员...");

        PageResult<Student> allStu = stuService.findAllStu(stuQueryParam);
        return Result.success(allStu);
    }

    @DeleteMapping("/{ids}")    //批量删除学员
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除学员:{}", ids);

        stuService.delete(ids);
        return Result.success();
    }

    @PostMapping    //添加学员
    public Result add(@RequestBody Student student) {
        log.info("添加学员:{}", student);

        stuService.add(student);
        return Result.success();
    }

    @GetMapping("/{id}")    //根据主键ID查询学员的信息(查询回显)
    public Result findById(@PathVariable Integer id) {
        log.info("根据主键ID查询学员的信息(查询回显):{}", id);

        Student stu = stuService.findById(id);
        return Result.success(stu);
    }

    @PutMapping //修改学员的数据信息
    public Result update(@RequestBody Student student) {
        log.info("修改学员的数据信息:{}", student);

        stuService.update(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")  //修改学员的违纪处理信息
    public Result updateViolation(@PathVariable Integer id, @PathVariable Short score) {
        log.info("修改学员的违纪处理信息:{},{}", id, score);

        stuService.updateViolation(id, score);
        return Result.success();
    }
}
