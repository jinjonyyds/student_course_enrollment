package com.ruthorTech.demo_student_course.service;

import com.ruthorTech.demo_student_course.entity.Course;
import com.ruthorTech.demo_student_course.entity.Student;
import com.ruthorTech.demo_student_course.repository.CourseRepository;
import com.ruthorTech.demo_student_course.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
    private final StudentRepository studentRepo;

    //get all students
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
    //get student by ID
    public Student getStudentById(Long studentId) {
        return studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
    }
    //create student
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    //update
    public Student updateStudent(Long studentId, Student student) {
        Student existingStudent = getStudentById(studentId);
        existingStudent.setName(student.getName());
        return studentRepo.save(existingStudent);
    }

    public void deleteStudent(Long studentId) {
        Student student = studentRepo.findById(studentId)
                        .orElseThrow(() -> new RuntimeException("Student not found"));
        student.getCourses().forEach(course -> course.getStudents().remove(student));
        studentRepo.delete(student);
    }


}
