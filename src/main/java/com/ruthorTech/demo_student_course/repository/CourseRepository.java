package com.ruthorTech.demo_student_course.repository;

import com.ruthorTech.demo_student_course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
