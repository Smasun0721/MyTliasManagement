package org.example.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tliaswebmanagement.exception.custom.ClassNotNull;
import org.example.tliaswebmanagement.mapper.ClazzMapper;
import org.example.tliaswebmanagement.mapper.EmpMapper;
import org.example.tliaswebmanagement.pojo.Clazz;
import org.example.tliaswebmanagement.pojo.ClazzQueryParam;
import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override       //分页查询班级列表
    public PageResult<Clazz> findAllClazz(ClazzQueryParam clazzQueryParam) {

        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> allClazz = clazzMapper.findAllClazz(clazzQueryParam);

        //设置额外元素
        for(Clazz clazz:allClazz){
            //匹配班主任姓名
            Integer masterId = clazz.getMasterId();
            if(masterId!=null){
                String masterName = empMapper.findById(masterId).getName();
                clazz.setMasterName(masterName);
            }
            //判断班级状态
            LocalDate beginDate = clazz.getBeginDate();
            LocalDate endDate = clazz.getEndDate();
            if(beginDate.isAfter(LocalDate.now())){
                clazz.setStatus("未开班");
            }else if(endDate.isBefore(LocalDate.now())){
                clazz.setStatus("已结课");
            }else{
                clazz.setStatus("在读中");
            }
        }

        Page<Clazz> p=(Page<Clazz>) allClazz;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override   //根据班级ID查询班级人数
    public Integer stuCountById(Integer id) {
        Integer stuCount = clazzMapper.stuCountById(id);
        return stuCount;
    }

    @Override   //删除班级(需检查当前班级下是否有学生)
    public void deleteById(Integer id) throws ClassNotNull {
       if(clazzMapper.stuCountById(id)!=0){
           throw new ClassNotNull("对不起, 该班级下有学生, 不能直接删除");
       }else {
           clazzMapper.deleteById(id);
       }
    }

    @Override   //添加班级
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    @Override   //根据id查询班级信息(编辑查询回显)
    public Clazz findById(Integer id) {
        Clazz clazz = clazzMapper.findById(id);
        return clazz;
    }

    @Override   //更新班级数据
    public void update(Clazz clazzData) {
        clazzData.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazzData);
    }

    @Override   //查询所有班级(下拉列表数据)
    public List<Clazz> findAll() {
        List<Clazz> allClazz = clazzMapper.findAll();
        return allClazz;

    }
}
