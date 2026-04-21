package org.example.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tliaswebmanagement.mapper.LogMapper;
import org.example.tliaswebmanagement.pojo.OperateLog;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;


    @Override
    public PageResult<OperateLog> findAllLog(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> allLog = logMapper.findAll();
        Page<OperateLog> p = (Page<OperateLog>) allLog;
        return new PageResult<OperateLog>(p.getTotal(), p.getResult());
    }
}
