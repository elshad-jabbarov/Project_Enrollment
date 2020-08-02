package com.example.jpademo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
public class Student {
    @Id
    @Positive
    @NotNull
    @Digits(integer = 10,fraction = 0)
    Integer studentId;



    @NotBlank
    @Size(min=2,max = 20)
    String firstName;

    @NotBlank
    @Size(min=2,max = 20)
    String lastName;


    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }


    String Major;


    @ManyToMany
    @JoinTable(name = "ENROLMENTS",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSEID"))
    @JsonIgnore
    List<Course> courses;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
