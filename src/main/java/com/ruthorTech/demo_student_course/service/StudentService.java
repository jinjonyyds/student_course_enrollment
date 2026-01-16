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
    private final CourseRepository courseRepo;

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
    public Student updateStudent(Long studentId, Student updatedStudent) {
        Student existingStudent = getStudentById(studentId);
        existingStudent.setName(updatedStudent.getName());
        if (updatedStudent.getCourses() != null) {

            existingStudent.getCourses().clear();

            for (Course course : updatedStudent.getCourses()) {
                Course existingCourse = courseRepo.findById(course.getId())
                        .orElseThrow(() -> new RuntimeException("Course not found: " + course.getId()));
                existingStudent.getCourses().add(existingCourse);
            }
        }
        return studentRepo.save(existingStudent);
    }

    public void deleteStudent(Long studentId) {
        Student student = studentRepo.findById(studentId)
                        .orElseThrow(() -> new RuntimeException("Student not found"));
        student.getCourses().forEach(course -> course.getStudents().remove(student));
        studentRepo.delete(student);
    }


}
