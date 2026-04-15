package com.react.service;

import java.util.List;

import com.react.model.Student;



public interface StudentService {
	
	public void saveRecord(Student s);

	List<Student> getAllData();

	void deleteData(int rollNo);
}
