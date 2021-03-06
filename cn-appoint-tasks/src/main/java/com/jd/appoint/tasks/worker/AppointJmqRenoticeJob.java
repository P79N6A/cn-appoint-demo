package com.jd.appoint.tasks.worker;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.jd.appoint.inner.tasks.TasksAppointFacade;
import com.jd.travel.base.soa.SoaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lijizhen1@jd.com
 * @date 2018/6/5 15:14
 */
@Component
public class AppointJmqRenoticeJob implements SimpleJob {
    private final Logger logger = LoggerFactory.getLogger(AppointJmqRenoticeJob.class);

    @Resource
    private TasksAppointFacade tasksAppointFacade;

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            tasksAppointFacade.noticeAppointInfo(new SoaRequest());
        } catch (Exception exc) {
            logger.error("noticeAppointInfo(),error:", exc);
        }
    }
}

