package com.example.jpademo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Entity
@Table(name = "COURSES")
public class Course {


    @Id
    @Positive
    @NotNull
    @Digits(integer = 10,fraction = 0)
    Integer courseID;
    @NotBlank
    @Size(min=2,max = 200)
    String Department;
    @NotBlank
    @Size(min=2,max = 200)
    String courseName;


    Integer Credit_hours;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    List<Student> students;

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
}
