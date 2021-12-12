package com.example.demo.quartz;

import org.quartz.*;

import static org.quartz.DateBuilder.dateOf;

public final class TimeUtil {

    private TimeUtil(){}

    public static Trigger buildTrigger(final Class jobClass, final SchedulerInfo info){
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(info.getRepeatIntervals());

        if(info.isRunForever()){
            builder = builder.repeatForever();
        }else {
            builder = builder.withRepeatCount(info.getTotalFireCount()-1);
        }

        return TriggerBuilder.newTrigger().withIdentity(jobClass.getSimpleName()).withSchedule(builder).startAt(dateOf(info.getStartHour(),0,0)).build();
    }

    public static JobDetail buildJobDetail(final Class jobClass, final SchedulerInfo info){
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(),info);

        return JobBuilder.newJob(jobClass).withIdentity(jobClass.getSimpleName()).setJobData(jobDataMap).build();
    }
}
