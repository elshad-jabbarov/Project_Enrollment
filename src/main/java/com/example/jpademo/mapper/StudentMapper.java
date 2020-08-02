package com.example.jpademo.mapper;

import com.example.jpademo.dto.StudentDTO;
import com.example.jpademo.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO getStudentDTO(Student student);

    List<StudentDTO> getStudentDTOList(Iterable<Student> students);

}
