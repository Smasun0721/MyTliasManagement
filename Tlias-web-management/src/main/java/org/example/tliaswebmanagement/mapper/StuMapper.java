package org.example.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.example.tliaswebmanagement.pojo.Clazz;
import org.example.tliaswebmanagement.pojo.ClazzCountOption;
import org.example.tliaswebmanagement.pojo.StuQueryParam;
import org.example.tliaswebmanagement.pojo.Student;

import java.util.List;
import java.util.Map;

@Mapper
public interface StuMapper {
    //查询学员列表
    List<Student> findAllStu(StuQueryParam stuQueryParam);
    //批量删除学员
    void delete(List<Integer> ids);
    //添加学员
    void add(Student student);
    //根据主键ID查询学员的信息(查询回显)
    Student findById(Integer id);
    //修改学员的数据信息
    void update(Student student);
    //统计学员的学历信息
    @MapKey("")
    List<Map<String,Object>> getStudentDegreeData();

    List<Map<String,Object>> getStudentCountData(List<Clazz> clazzList);
}
