package com.example.jpademo.dto;

import com.example.jpademo.entity.Student;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.*;
import java.util.List;
public class CourseDTO {

    Integer courseID;

    String Department;

    String courseName;
    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredit_hours() {
        return Credit_hours;
    }

    public void setCredit_hours(Integer credit_hours) {
        Credit_hours = credit_hours;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    Integer Credit_hours;


    List<Student> students;

}
