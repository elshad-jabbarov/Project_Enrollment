package com.example.jpademo.controller;

import com.example.jpademo.dto.StudentDTO;
import com.example.jpademo.entity.Course;
import com.example.jpademo.entity.Student;
import com.example.jpademo.repo.CourseRepository;
import com.example.jpademo.repo.StudentRepository;

import com.example.jpademo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student/")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StudentRepository repository;

    @Autowired
    CourseRepository CourseRepository;
    @Autowired
    StudentService studentService;

    @PostMapping("add")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        String result = studentService.addStudent(student);
        if (result.indexOf("Error") >= 0)
            return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Integer id) {
        StudentDTO studentDTO = studentService.getStudent(id);
        if (studentDTO == null) {
            return new ResponseEntity<>(studentDTO, HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        List<StudentDTO> studentDTO = studentService.getStudents();
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    @Transactional
    public ResponseEntity<String> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
        String result = studentService.updateStudent(id, student);
        if (result.contains("ERROR")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/")
    public String getIndex() {
        return "students/index";
    }

    @GetMapping("/list")
    public String getStudentList(Model model) {
        Iterable<Student> students = repository.findAll();
        model.addAttribute("students", students);
        return "students/list";
    }


    @PostMapping("save")
    public String addStudent(Model model, @Valid @ModelAttribute("student") Student student, BindingResult br) {
        logger.debug("addStudent() used");

        if (br.hasErrors()) {
            logger.error("addStudent(): error happened in validation");
            return "students/modify";
        }
        logger.info("addStudent(): student " + "student" + " added");
        repository.save(student);
        return getStudentList(model);

    }


    @GetMapping("/modify")
    public String showNEwPage(Model model) {
        Student st = new Student();
        model.addAttribute("student", st);
        return "students/modify";
    }

    @GetMapping("/update")
    public String showUpdatePage(Model model, @RequestParam("id") Integer Id) {
        Optional<Student> result = repository.findByStudentId(Id);
        result.ifPresent(student -> model.addAttribute("student", student));
        Iterable<Course> courses = CourseRepository.findAll();
        model.addAttribute("allCourses", courses);
        return "students/modify";
    }

    @PostMapping("/delete")
    public String deleteStudent(Model model, @RequestParam("id") Integer id) {
        repository.deleteById(id);
        return getStudentList(model);
    }


    @GetMapping("/search")
    public String searchStudents(Model model, @RequestParam("id") Integer Id, @RequestParam("first") String firstName, @RequestParam("last") String lastName) {
        List<Student> students = repository.findByStudentIdOrFirstNameOrLastName(Id, firstName, lastName);
        model.addAttribute("students", students);
        return "students/list";
    }
}

