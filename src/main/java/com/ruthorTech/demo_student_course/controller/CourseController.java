package com.ruthorTech.demo_student_course.controller;

import com.ruthorTech.demo_student_course.entity.Course;
import com.ruthorTech.demo_student_course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService service;

    // get all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    // get course by id
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getCourseById(courseId));
    }

    // create course
    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        Course created = service.createCourse(course);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // update course
    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long courseId,
            @RequestBody Course course) {

        Course updated = service.updateCourse(courseId, course);
        return ResponseEntity.ok(updated);
    }

    // delete course
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        service.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
