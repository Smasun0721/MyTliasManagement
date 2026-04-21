package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.pojo.OperateLog;
import org.example.tliaswebmanagement.pojo.PageResult;

public interface LogService {

    PageResult<OperateLog> findAllLog(Integer page, Integer pageSize);

}
