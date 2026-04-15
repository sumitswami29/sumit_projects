package com.react.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.react.model.Student;
import com.react.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public void saveRecord(Student s) {
		studentRepository.save(s);
	}
	
	@Override
	public List<Student> getAllData(){
		return studentRepository.findAll();
	}
	
	@Override
	public void deleteData(int rollNo) {
		studentRepository.deleteById(rollNo);
	}
	
	
	

}
