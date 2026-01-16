package com.ruthorTech.demo_student_course.controller;

import com.ruthorTech.demo_student_course.entity.Student;
import com.ruthorTech.demo_student_course.service.EnrollmentService;
import com.ruthorTech.demo_student_course.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;
    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = service.getAllStudents();
        return ResponseEntity.ok(students);
    }
    //get student by id
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Student student = service.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }
    //create
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student created = service.createStudent(student);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student studentDetails) {
        Student updatedStudent = service.updateStudent(studentId, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }
    //delete
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        service.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{studentId}/courses/{courseId}")
    public Student enrollCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return enrollmentService.enroll(studentId, courseId);
    }
}
