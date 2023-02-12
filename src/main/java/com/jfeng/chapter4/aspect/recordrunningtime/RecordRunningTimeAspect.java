package com.jfeng.chapter4.aspect.recordrunningtime;

import com.jfeng.framework.aop.annotation.Aspect;
import com.jfeng.framework.aop.core.AbstractAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 计算运行时间
 */
@Aspect(RecordRunningTime.class)
public class RecordRunningTimeAspect extends AbstractAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordRunningTimeAspect.class);

    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.debug("---------- begin ----------");
        LOGGER.debug(String.format("class: %s", cls.getName()));
        LOGGER.debug(String.format("method: %s", method.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOGGER.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
        LOGGER.debug("---------- end ----------");
    }
}
