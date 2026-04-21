package org.example.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.OperateLog;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log/page")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping
    public Result findAllLog(Integer page,Integer pageSize){
        log.info("查询日志信息...第{}页,{}条",page,pageSize);

        PageResult<OperateLog> allLog = logService.findAllLog(page, pageSize);
        return Result.success(allLog);
    }
}
