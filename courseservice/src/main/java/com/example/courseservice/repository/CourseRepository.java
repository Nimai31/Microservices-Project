package com.example.courseservice.repository;

import com.example.courseservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Date;

// Repository for accessing Task data from the database
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUserEmail(String email);
    List<Course> findByUserEmailAndDueDateBeforeAndStatusAndArchived(String userEmail, Date dueDate, String status, boolean archived);// Query to fetch tasks by user email
    List<Course> findByUserEmailAndDueDateBetweenAndStatusAndArchived(String userEmail, Date startDate, Date endDate, String status, boolean archived);
}
