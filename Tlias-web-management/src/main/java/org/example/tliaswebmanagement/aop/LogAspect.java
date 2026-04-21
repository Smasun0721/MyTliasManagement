package org.example.tliaswebmanagement.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.tliaswebmanagement.mapper.OperateLogMapper;
import org.example.tliaswebmanagement.pojo.OperateLog;
import org.example.tliaswebmanagement.util.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 操作日志切面
 * 拦截所有添加了@Log注解的方法，记录操作日志
 */

@Aspect
@Component
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 环绕通知：记录操作日志
     */
    @Around("@annotation(org.example.tliaswebmanagement.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 记录开始时间
        long startTime = System.currentTimeMillis();

        // 2. 执行目标方法
        Object result = null;
        try {
            result = joinPoint.proceed();
        } finally {

            OperateLog log = new OperateLog();

            // 1. 操作人ID (实际项目中替换为您的用户ID获取方式)
            log.setOperateEmpId(getCurrentUserId()); // 示例ID，应替换为实际用户ID获取逻辑

            // 2. 操作时间
            log.setOperateTime(LocalDateTime.now());

            // 3. 类名和方法名
            log.setClassName(joinPoint.getTarget().getClass().getName());
            log.setMethodName(joinPoint.getSignature().getName());

            // 4. 方法参数 (直接toString，简单粗暴)
            log.setMethodParams(Arrays.toString(joinPoint.getArgs()));

            // 5. 方法返回值
            log.setReturnValue(result != null ? result.toString() : "void");

            // 6. 执行耗时
            log.setCostTime(System.currentTimeMillis() - startTime);

            // 7. 保存到数据库
            operateLogMapper.insert(log);
        }
        return result;
    }

    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}


