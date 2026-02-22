package edu.cjc.sma.app.servicei;

import java.util.List;

import edu.cjc.sma.app.model.Student;

public interface StudentServiceI {

	public void saveData(Student s);
	public List<Student> getAllStudents();
	public void removeStudent(int studentId);
	public List<Student> getStudentsByBatch(String batchNumber);
	public List<Student> paging(int pageNo, int size);
	public Student getSingleStudent(int studentId);
	public void updateStudentFees(int studentId, double ammount);
	void updateStudentBatch(int id, String batchNumber, String batchMode);


}
