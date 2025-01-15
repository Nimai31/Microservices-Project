package com.example.courseservice.service;

import com.example.courseservice.model.Course;
import com.example.courseservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Create a task and associate it with a user based on email
    public void createTask(Course task) {
        // Save task to the database
        courseRepository.save(task);
    }

    // Fetch tasks by user email
    public List<Course> getTasksByUserEmail(String email) {
        return courseRepository.findByUserEmail(email);
    }

    // Update a task
    public String updateTask(Long taskId, Course taskDetails) {
        Course task = courseRepository.findById(taskId).orElse(null);
        if (task != null) {
            // Update the task fields
            task.setTaskName(taskDetails.getTaskName());
            task.setDescription(taskDetails.getDescription());
            task.setPriority(taskDetails.getPriority());
            task.setDueDate(taskDetails.getDueDate());

            // Save the updated task
            courseRepository.save(task);

            return "Task updated successfully!";
        } else {
            return "Task not found!";
        }
    }

    public String updateTaskStatus(Long taskId, String status) {
        Course task = courseRepository.findById(taskId).orElse(null);
        if (task != null) {
            task.setStatus(status); // Set status to Completed or Pending
            courseRepository.save(task);
            return "Task status updated to " + status + " successfully!";
        } else {
            return "Task not found!";
        }
    }

    // Mark task as completed
    public String completeTask(Long taskId) {
        Course task = courseRepository.findById(taskId).orElse(null);
        if (task != null) {
            task.setStatus("Completed");
            courseRepository.save(task);
            return "Task completed successfully!";
        } else {
            return "Task not found!";
        }
    }

    // Archive task
    public String archiveTask(Long taskId) {
        Course task = courseRepository.findById(taskId).orElse(null);
        if (task != null) {
            task.setArchived(true);
            courseRepository.save(task);
            return "Task archived successfully!";
        } else {
            return "Task not found!";
        }
    }

    public List<Course> getTasksNearDeadline(String userEmail) {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime(); // Current date

        calendar.add(Calendar.DAY_OF_MONTH, 3); // Add 3 days to today
        Date upcomingDeadline = calendar.getTime(); // Date after 3 days

        // Query tasks for the specific user with status "Pending" and archived = false
        return courseRepository.findByUserEmailAndDueDateBetweenAndStatusAndArchived(userEmail, today, upcomingDeadline, "Pending", false);
    }

    public String unarchiveTask(Long taskId) {
        Course task = courseRepository.findById(taskId).orElse(null);
        if (task != null) {
            task.setArchived(false); // Set task as unarchived
            courseRepository.save(task);
            return "Task unarchived successfully!";
        } else {
            return "Task not found!";
        }
    }

    public List<Course> getTasksOverdue(String userEmail) {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime(); // Current date

        // Query tasks for the specific user with status "Pending", archived = false, and due date before today
        return courseRepository.findByUserEmailAndDueDateBeforeAndStatusAndArchived(userEmail, today, "Pending", false);
    }

    public List<Course> getTasksUpcoming(String userEmail) {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime(); // Current date

        calendar.add(Calendar.DAY_OF_MONTH, 3); // Add 3 days to today
        Date upcomingDeadline = calendar.getTime(); // Date after 3 days

        // Query tasks for the specific user with status "Pending", archived = false, and due date between today and the upcoming deadline
        return courseRepository.findByUserEmailAndDueDateBetweenAndStatusAndArchived(userEmail, today, upcomingDeadline, "Pending", false);
    }

}
