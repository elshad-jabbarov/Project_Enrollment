package com.example.jpademo.controller;

import com.example.jpademo.dto.CourseDTO;
import com.example.jpademo.entity.Course;
import com.example.jpademo.exceptions.DeleteException;
import com.example.jpademo.exceptions.GlobalExceptionHandler;
import com.example.jpademo.repo.CourseRepository;
import com.example.jpademo.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/course")
public class CourseController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;
    @Autowired
    CourseService courseService;

    @GetMapping("{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Integer id) {
        CourseDTO courseDTO = courseService.getCourse(id);
        if (courseDTO == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courseDTOList = courseService.getCourseList();
        return new ResponseEntity<>(courseDTOList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public String getCourseList(Model model) {
        Iterable<Course> courses = repository.findAll();
        model.addAttribute("courses", courses);

        return "students/courses";
    }

    @GetMapping("/add")
    public String showNEwPage(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        return "students/add";
    }

    @GetMapping("/save")
    public String addStudent(Model model, @Valid @ModelAttribute("course") Course course, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("addStudent(): error happened in validation");
            return "students/add";
        }
        repository.save(course);
        return getCourseList(model);

    }


    @GetMapping("/update")
    public String showUpdatePage(Model model, @RequestParam("id") Integer Id) {
        Optional<Course> result = repository.findById(Id);
        result.ifPresent(course -> model.addAttribute("course", course));
        return "students/add";
    }

    @PostMapping("/delete")
    public String deleteCourse(Model model, @RequestParam("id") Integer id) throws DeleteException {
        try {
            repository.deleteById(id);

            return getCourseList(model);
        } catch (Exception ex) {
            throw new DeleteException("You can ony delete empty courses");
        }
    }

    @GetMapping("/search")
    public String searchStudents(Model model, @RequestParam("id") Integer Id, @RequestParam("name") String name) {
        List<Course> courses = repository.findByCourseIDOrName(Id, name);
        model.addAttribute("courses", courses);
        return "students/courses";
    }


}
