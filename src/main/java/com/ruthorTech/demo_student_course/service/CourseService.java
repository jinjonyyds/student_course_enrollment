package com.ruthorTech.demo_student_course.service;

import com.ruthorTech.demo_student_course.entity.Course;
import com.ruthorTech.demo_student_course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepo;

    // get all courses
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    // get course by id
    public Course getCourseById(Long courseId) {
        return courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    // create course
    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    // update course
    public Course updateCourse(Long courseId, Course course) {
        Course existingCourse = getCourseById(courseId);
        existingCourse.setName(course.getName());
        return courseRepo.save(existingCourse);
    }

    // delete course
    public void deleteCourse(Long courseId) {
        courseRepo.deleteById(courseId);
    }
}
