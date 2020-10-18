package com.aniu.quartz.schedule;

import com.aniu.quartz.dao.TaskDao;
import com.aniu.quartz.entity.Task;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    TaskDao taskDao;

    @Autowired
    Scheduler scheduler;

    @Scheduled(cron = "0/10 * * * * ?")
    public void refreshScheduler() throws SchedulerException, ClassNotFoundException {

        System.out.println("refreshScheduler");
        List<Task> tasks = taskDao.selectTask();


        for(Task task : tasks) {
            String corn = task.getCorn();
            String job = task.getJob();
            String jobName= task.getJobName();
            String jobGroup = task.getJobGroup();
            String triggerName = task.getTriggerName();
            String triggerGroup = task.getTriggerGroup();

            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,jobGroup);
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //当前任务未调度
            if(cronTrigger == null){
                JobDetail jobDetail =
                        JobBuilder.newJob((Class<? extends Job>)Class.forName(jobName))
                        .withIdentity(jobName,jobGroup)
                        .build();

                CronScheduleBuilder cronScheduleBuilder =
                        CronScheduleBuilder.cronSchedule(corn);

                cronTrigger = TriggerBuilder.newTrigger()
                        .withIdentity(jobName,jobGroup)
                        .withSchedule(cronScheduleBuilder)
                        .build();

                scheduler.scheduleJob(jobDetail,cronTrigger);
            //当前任务已经调度
            } else {
                String cronExpression = cronTrigger.getCronExpression();
                if(!corn.equals(cronExpression)){
                    CronScheduleBuilder cronScheduleBuilder =
                            CronScheduleBuilder.cronSchedule(corn);

                    cronTrigger = cronTrigger.getTriggerBuilder()
                            .withIdentity(triggerKey)
                            .withSchedule(cronScheduleBuilder)
                            .build();

                    scheduler.rescheduleJob(triggerKey,cronTrigger);
                }
            }

        }

    }

}
