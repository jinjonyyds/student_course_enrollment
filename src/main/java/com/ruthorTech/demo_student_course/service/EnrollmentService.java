package com.ruthorTech.demo_student_course.service;

import com.ruthorTech.demo_student_course.entity.Course;
import com.ruthorTech.demo_student_course.entity.Student;
import com.ruthorTech.demo_student_course.repository.CourseRepository;
import com.ruthorTech.demo_student_course.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentService {
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public Student enroll(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (student.getCourses().contains(course)) {
            throw new RuntimeException("Student already enrolled");
        }
        student.getCourses().add(course);
        course.getStudents().add(student);
        return studentRepo.save(student);
    }
}
