For Students:
1) To get a student: http://localhost:8080/student/{id} (GET)
Example  http://localhost:8080/student/1
2) To get a all  students: http://localhost:8080/student/all (GET)
3)To add a student: http://localhost:8080/student/add (POST)
  {
   "studentId": "12",
   "firstName":"tural",
   "lastName": "Salimov",
    "major": "CSS"
  }
4)To update a student: http://localhost:8080/student/update/{id} (POST)
 Example http://localhost:8080/student/update/1
         {
          "firstName":"walter",
          "lastName": "white",
           "major": "chemistry"
         }
For Courses:
1) To get a course: http://localhost:8080/course/{id} (GET)
2) To get all  courses: http://localhost:8080/course/all (GET)


