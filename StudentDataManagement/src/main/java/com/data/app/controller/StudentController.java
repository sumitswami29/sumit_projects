package com.data.app.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.app.model.Student;
import com.data.app.model.User;
import com.data.app.repository.StudentRepository;
import com.data.app.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @Autowired
    private UserRepository userRepo;

    // ADD
    @PostMapping("/{userId}")
    public Student addStudent(@RequestBody Student s, @PathVariable int userId) {
        User user = userRepo.findById(userId).get();
        s.setUser(user);
        return repo.save(s);
    }

    // GET USER DATA
    @GetMapping("/{userId}")
    public List<Student> getStudents(@PathVariable int userId) {
        return repo.findByUserId(userId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student update(@PathVariable int id, @RequestBody Student s) {
        s.setId(id);
        return repo.save(s);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }
}
