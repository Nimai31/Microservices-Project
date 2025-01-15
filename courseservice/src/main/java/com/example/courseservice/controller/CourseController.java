package com.example.courseservice.controller;

import com.example.courseservice.model.Course;
import com.example.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Endpoint to create a task
    @PostMapping("/create")
    public String createTask(@RequestBody Course task) {
        courseService.createTask(task); // Call service to save task
        return "Task created successfully!";
    }

    // Endpoint to get tasks by user email
    @GetMapping("/user/{email}")
    public List<Course> getTasksByUserEmail(@PathVariable String email) {
        return courseService.getTasksByUserEmail(email);
    }

    // Endpoint to update a task
    @PutMapping("/update/{taskId}")
    public String updateTask(@PathVariable Long taskId, @RequestBody Course task) {
        return courseService.updateTask(taskId, task);
    }

    // Endpoint to archive a task
    @PutMapping("/archive/{taskId}")
    public String archiveTask(@PathVariable Long taskId) {
        return courseService.archiveTask(taskId);
    }

    @GetMapping("/overdue")
    public List<Map<String, Object>> getOverdueTasks(@RequestParam String userEmail) {
        // Fetch overdue tasks for the specific user
        List<Course> overdueTasks = courseService.getTasksOverdue(userEmail);

        // Map each task to a map with required fields
        return overdueTasks.stream().map(task -> Map.<String, Object>of(
                "taskName", task.getTaskName(),
                "userEmail", task.getUserEmail(),
                "dueDate", task.getDueDate()
        )).collect(Collectors.toList());
    }

    @PutMapping("/complete/{taskId}")
    public String completeTask(@PathVariable Long taskId) {
        return courseService.updateTaskStatus(taskId, "Completed");
    }

    @PutMapping("/pending/{taskId}")
    public String pendingTask(@PathVariable Long taskId) {
        return courseService.updateTaskStatus(taskId, "Pending");
    }

    @PutMapping("/unarchive/{taskId}")
    public String unarchiveTask(@PathVariable Long taskId) {
        return courseService.unarchiveTask(taskId);
    }

    @GetMapping("/upcoming")
    public List<Map<String, Object>> getUpcomingTasks(@RequestParam String userEmail) {
        // Fetch upcoming tasks for the specific user
        List<Course> upcomingTasks = courseService.getTasksUpcoming(userEmail);

        // Map each task to a map with required fields
        return upcomingTasks.stream().map(task -> Map.<String, Object>of(
                "taskName", task.getTaskName(),
                "userEmail", task.getUserEmail(),
                "dueDate", task.getDueDate()
        )).collect(Collectors.toList());
    }

}
