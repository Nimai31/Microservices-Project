package com.example.notificationservice.controller;

import com.example.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Notify users about upcoming task deadlines
    @GetMapping("/notify-upcoming")
    public String notifyUpcomingDeadlines() {
        notificationService.notifyUpcomingDeadlines();
        return "Upcoming task notifications sent successfully!";
    }

    // Notify users about overdue tasks
    @GetMapping("/notify-overdue")
    public String notifyOverdueTasks() {
        notificationService.notifyOverdueTasks();
        return "Overdue task notifications sent successfully!";
    }

    // Manually trigger email notification
    @PostMapping("/send-email")
    public String sendEmailNotification(@RequestBody Map<String, String> emailDetails) {
        String to = emailDetails.get("to");
        String subject = emailDetails.get("subject");
        String message = emailDetails.get("message");
        notificationService.sendEmailNotification(to, subject, message);
        return "Email sent successfully to " + to;
    }


    @GetMapping("/overdue-notifications")
    public List<Map<String, String>> getOverdueNotifications(@RequestParam String userEmail) {
        // Call the service method to get overdue task notifications for the specific user
        return notificationService.getOverdueTaskNotifications(userEmail);
    }

    @GetMapping("/upcoming-notifications")
    public List<Map<String, String>> getUpcomingNotifications(@RequestParam String userEmail) {
        // Call the service method to get upcoming task notifications for the specific user
        return notificationService.getUpcomingTaskNotifications(userEmail);
    }


}
