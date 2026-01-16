package com.ruthorTech.demo_student_course.repository;

import com.ruthorTech.demo_student_course.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
