package com.data.app.controller;

import com.data.app.model.Student;
import com.data.app.service.StudentServcieInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/api")
public class HomeController {

    @Autowired
    StudentServcieInt ssi;

   
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        ssi.addStudentData(student);
        return student;
    }

   
    @GetMapping("/students")
    public List<Student> getStudents() {
        return ssi.showStudentData();
    }

   
    @PostMapping("/login")
    public String login(@RequestBody Student student) {
        if(student.getName().equals("admin") && student.getPassword().equals("admin")){
            return "success";
        }
        return "fail";
    }
}
