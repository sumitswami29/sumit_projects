package com.data.app.service;

import com.data.app.model.Student;

import java.util.List;


public interface StudentServcieInt {
	
	public void addStudentData(Student student);
	public List<Student> showStudentData();

}