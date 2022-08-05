package com.gu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Configuration
@Component
@ComponentScan("com.gu")
public class SchedulerThread {
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);//我这里设置的线程数是2,可以根据需求调整
        taskScheduler.setThreadNamePrefix("taskExecutor-");   // 线程名称
        taskScheduler.setAwaitTerminationSeconds(60);         // 等待时长
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);  // 调度器shutdown被调用时等待当前被调度的任务完成
        return taskScheduler;
    }



}
