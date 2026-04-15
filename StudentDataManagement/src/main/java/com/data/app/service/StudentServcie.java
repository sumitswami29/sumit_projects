package com.data.app.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.data.app.model.Student;
import com.data.app.repository.StudentRepository;
import java.util.List;

@Service
public class StudentServcie implements StudentServcieInt {
	
	@Autowired
	StudentRepository sri;

	@Override
	public void addStudentData(Student student) {
		System.out.println("In servie class: "+ student);
		sri.save(student);
		
		
	}
	@Override
	public List <Student>  showStudentData() {
		List <Student> list = sri.findAll();
		return list;
		
	}
	


}
