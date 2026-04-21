package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.pojo.ClazzCountOption;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.StuQueryParam;
import org.example.tliaswebmanagement.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StuService {

    //查询学员列表
    PageResult<Student> findAllStu(StuQueryParam stuQueryParam);
    //批量删除学员
    void delete(List<Integer> ids);
    //添加学员
    void add(Student student);
    //根据主键ID查询学员的信息(查询回显)
    Student findById(Integer id);
    //修改学员的数据信息
    void update(Student student);
    //修改学员的违纪处理信息
    void updateViolation(Integer id, Short score);
    //统计学员的学历信息
    List<Map<String, Object>> getStudentDegreeData();
    //统计班级的人数信息
    ClazzCountOption getStudentCountData();
}
