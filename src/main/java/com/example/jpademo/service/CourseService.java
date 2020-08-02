package com.example.jpademo.service;

import com.example.jpademo.dto.CourseDTO;
import com.example.jpademo.entity.Course;
import com.example.jpademo.mapper.CourseMapper;
import com.example.jpademo.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository repository;

    public CourseDTO getCourse(Integer courseId) {
        Optional<Course> result = repository.findById(courseId);
        CourseDTO courseDTO = null;
        if (result.isPresent()) {
            Course course = result.get();
            courseDTO = CourseMapper.INSTANCE.getCourseDTO(course);
        }
        return courseDTO;
    }

    public List<CourseDTO> getCourseList() {
        Iterable<Course> courses = repository.findAll();
        return CourseMapper.INSTANCE.getCourseDTOList(courses);
    }
}
