package org.example.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.tliaswebmanagement.pojo.Clazz;
import org.example.tliaswebmanagement.pojo.ClazzQueryParam;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> findAllClazz(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void add(Clazz clazz);

    Clazz findById(Integer id);

    void update(Clazz clazzData);

    //根据班级ID查询班级人数
    Integer stuCountById(Integer id);
    //查询所有班级(下拉列表数据)
    List<Clazz> findAll();
}
