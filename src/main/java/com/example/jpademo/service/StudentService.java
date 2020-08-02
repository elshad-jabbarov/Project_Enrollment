package com.example.jpademo.service;

import com.example.jpademo.dto.StudentDTO;
import com.example.jpademo.entity.Student;
import com.example.jpademo.mapper.StudentMapper;
import com.example.jpademo.repo.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional

public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String addStudent(Student student) {
        try {
            studentRepository.save(student);
            return " Student added";
        } catch (Exception ex) {
            return "error happened" + ex;
        }
    }

    public StudentDTO getStudent(Integer id) {
        Optional<Student> optional = studentRepository.findByStudentId(id);
        StudentDTO studentDTO = null;
        if (optional.isPresent()) {
            Student student = optional.get();
            studentDTO = StudentMapper.INSTANCE.getStudentDTO(student);
        }
        return studentDTO;
    }

    public List<StudentDTO> getStudents() {
        Iterable<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = StudentMapper.INSTANCE.getStudentDTOList(students);
        return studentDTOS;
    }

    public String updateStudent(Integer id, Student student) {
        Optional<Student> result = studentRepository.findById(id);
        studentRepository.updateStudent(id, student.getFirstName(), student.getLastName(), student.getMajor());
        return "student updated";
    }
}
