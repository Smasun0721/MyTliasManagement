package org.example.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.ClazzCountOption;
import org.example.tliaswebmanagement.pojo.JobOption;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.pojo.Student;
import org.example.tliaswebmanagement.service.EmpService;
import org.example.tliaswebmanagement.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private EmpService empService;
    @Autowired
    private StuService stuService;

    @GetMapping("/empJobData")
    public Result getEmpjobData() {
        log.info("统计各职位人员数量...");

        JobOption jobOption = empService.getempJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别数量...");

        List<Map<String, Object>> empGenderDataList = empService.getEmpGenderData();
        return Result.success(empGenderDataList);
    }

    @GetMapping("/studentDegreeData")   //统计学员的学历信息
    public Result getStudentDegreeData() {
        log.info("统计学员的学历信息...");

        List<Map<String, Object>> list = stuService.getStudentDegreeData();
        return Result.success(list);
    }

    @GetMapping("/studentCountData")   //统计班级的人数信息
    public Result getStudentCountData() {
        log.info("统计班级的人数信息...");

        ClazzCountOption studentCountData = stuService.getStudentCountData();
        return Result.success(studentCountData);
    }

}
