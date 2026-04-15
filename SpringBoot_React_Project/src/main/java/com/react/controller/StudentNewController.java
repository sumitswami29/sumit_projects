package com.react.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.react.model.Student;
import com.react.service.StudentService;

@RestController
@CrossOrigin
public class StudentNewController {
	
	@Autowired
	StudentService  studentservice;
	
	@PostMapping("/post")
	public Student insertData(@RequestBody Student s) {
	studentservice.saveRecord(s);
	return s; 
	}
	
	
	@GetMapping("/getAll")
	public List<Student> getALL(){
		List<Student> list = studentservice.getAllData();
		return list;
	}
	
	@DeleteMapping("/delete/{rollNo}")
	public String delete(@PathVariable Integer rollNo) {
		studentservice.deleteData(rollNo);
		return "Record Deleted";
	}

}
