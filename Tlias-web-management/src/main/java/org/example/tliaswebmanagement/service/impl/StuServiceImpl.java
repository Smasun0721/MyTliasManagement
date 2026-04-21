package org.example.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tliaswebmanagement.mapper.ClazzMapper;
import org.example.tliaswebmanagement.mapper.StuMapper;
import org.example.tliaswebmanagement.pojo.ClazzCountOption;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.StuQueryParam;
import org.example.tliaswebmanagement.pojo.Student;

import org.example.tliaswebmanagement.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;
    @Autowired
    private ClazzMapper clazzMapper;

    @Override   //查询学员列表
    public PageResult<Student> findAllStu(StuQueryParam stuQueryParam) {
        PageHelper.startPage(stuQueryParam.getPage(), stuQueryParam.getPageSize());
        List<Student> allStu = stuMapper.findAllStu(stuQueryParam);
        Page<Student> p = (Page<Student>) allStu;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }

    @Override   //批量删除学员
    public void delete(List<Integer> ids) {
        stuMapper.delete(ids);
    }

    @Override   //添加学员
    public void add(Student student) {
        //设置创建时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.add(student);
    }

    @Override   //根据主键ID查询学员的信息(查询回显)
    public Student findById(Integer id) {
        Student stu = stuMapper.findById(id);
        return stu;
    }

    @Override   //修改学员的数据信息
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.update(student);
    }

    @Override   //修改学员的违纪处理信息
    public void updateViolation(Integer id, Short score) {
        //检查score是否为null
        if (score != null) {
            //设置违纪分数与次数
            Student stu = stuMapper.findById(id);

            Short violationScore = stu.getViolationScore();
            violationScore = (short) (violationScore + score);
            //违纪分数降至0以下时，强制归零
            if (violationScore < 0) {
                violationScore = 0;
            }
            stu.setViolationScore(violationScore);
            //设置违纪次数(仅当违纪分数大于0时增加)
            if (violationScore > 0) {
                Short violationCount = stu.getViolationCount();
                stu.setViolationCount(++violationCount);
            }

            //更新操作时间
            stu.setUpdateTime(LocalDateTime.now());
            stuMapper.update(stu);
        }
    }

    @Override   //统计学员的学历信息
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> studentDegreeData = stuMapper.getStudentDegreeData();
        return studentDegreeData;
    }

    @Override   //统计班级的人数信息
    public ClazzCountOption getStudentCountData() {
        List<Map<String, Object>> list = stuMapper.getStudentCountData(clazzMapper.findAll());

        List<String> clazzName = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        for (Map<String, Object> map : list) {
            String name = map.get("clazzName").toString();
            Long d = (Long) map.get("data");
            clazzName.add(name);
            data.add(d);
        }
        ClazzCountOption clazzCountOption = new ClazzCountOption(clazzName, data);

        return clazzCountOption;
    }
}
