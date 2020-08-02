package com.example.jpademo.mapper;


import com.example.jpademo.dto.CourseDTO;
import com.example.jpademo.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDTO getCourseDTO(Course course);

    List<CourseDTO> getCourseDTOList(Iterable<Course> courses);
}
