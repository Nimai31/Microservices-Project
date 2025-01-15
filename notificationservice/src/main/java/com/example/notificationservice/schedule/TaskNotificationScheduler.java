package com.example.notificationservice.schedule;

import com.example.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskNotificationScheduler {

    @Autowired
    private NotificationService notificationService;

    // Run this task every hour to check for tasks nearing their deadline
    @Scheduled(cron = "0 0 * * * ?")  // Cron expression to run the task every hour
    public void checkForUpcomingTaskDeadlines() {
        // Notify users about tasks nearing their deadlines
        notificationService.notifyUpcomingDeadlines();
    }

    // Run this task every hour to check for overdue tasks
    @Scheduled(cron = "0 0 * * * ?")  // Cron expression to run the task every hour
    public void checkForOverdueTasks() {
        // Notify users about overdue tasks
        notificationService.notifyOverdueTasks();
    }
}
