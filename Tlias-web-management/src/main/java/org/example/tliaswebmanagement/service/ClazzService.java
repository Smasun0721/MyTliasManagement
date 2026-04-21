package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.exception.custom.ClassNotNull;
import org.example.tliaswebmanagement.pojo.Clazz;
import org.example.tliaswebmanagement.pojo.ClazzQueryParam;
import org.example.tliaswebmanagement.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    PageResult<Clazz> findAllClazz(ClazzQueryParam clazzQueryParam);

    //根据班级ID查询班级人数
    Integer stuCountById(Integer id);

    void deleteById(Integer id) throws ClassNotNull;

    void add(Clazz clazz);

    Clazz findById(Integer id);

    void update(Clazz clazzData);

    List<Clazz> findAll();
}
