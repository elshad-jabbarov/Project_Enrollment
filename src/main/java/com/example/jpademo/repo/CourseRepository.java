package com.example.jpademo.repo;

import com.example.jpademo.entity.Course;
import com.example.jpademo.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course,Integer> {

    @Query("SELECT c FROM Course c where c.courseID=:Id or c.courseName =:name ")
    Optional<Course> findByCredentials(Integer Id, String name);

    @Query(value = "SELECT * FROM COURSES   where COURSEID=:Id or COURSE_NAME = :name",nativeQuery = true)
    List<Course> findByCourseIDOrName(Integer Id, String name);


}
