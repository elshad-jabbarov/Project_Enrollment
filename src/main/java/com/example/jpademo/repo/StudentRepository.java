package com.example.jpademo.repo;

import com.example.jpademo.entity.Student;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student,Integer> {

    Optional<Student> findByStudentId(Integer Id);

    @Query(value = "SELECT * FROM STUDENT   where STUDENT_ID=:Id or FIRST_NAME=:firsName or LAST_NAME=:lastName",nativeQuery = true)
    List<Student> findByStudentIdOrFirstNameOrLastName(Integer Id,String firsName,String lastName);

    @Modifying
    @Query(value = "UPDATE STUDENT SET FIRST_NAME = :firstName, LAST_NAME = :lastName, MAJOR = :major WHERE STUDENT_ID = :studentId", nativeQuery = true)
    void updateStudent(Integer studentId, String firstName, String lastName, String major);
}
