package edu.cjc.sma.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.cjc.sma.app.model.Student;
import edu.cjc.sma.app.repositary.StudentRepositary;
import edu.cjc.sma.app.servicei.StudentServiceI;

@Service
public class StudentServiceImpl implements StudentServiceI
{

	@Autowired
	StudentRepositary sr;
	
	@Override
	public void saveData(Student s) {
		sr.save(s);
	}

	@Override
	public List<Student> getAllStudents() {

		return sr.findAll();
	}

	@Override
	public void removeStudent(int studentId) {
		sr.deleteById(studentId);
		
	}

	@Override
	public List<Student> getStudentsByBatch(String batchNumber) {
		
		return sr.findAllByBatchNumber(batchNumber);
	}

	@Override
	public List<Student> paging(int pageNo, int size) {
		              List<Student> l=sr.findAll(PageRequest.of(pageNo, size)).getContent();
		return l;
	}

	@Override
	public Student getSingleStudent(int studentId) {
		// TODO Auto-generated method stub
		return sr.findById(studentId).get();
	}

	@Override
	public void updateStudentFees(int studentId, double ammount) {
		
		       Student s    =  sr.findById(studentId).get();
		             s.setFeesPaid(s.getFeesPaid()+ammount);
		             sr.save(s);
	}
	@Override
	public void updateStudentBatch(int id, String batchNumber, String batchMode) {
	    Student s = sr.findById(id).get();
	    s.setBatchNumber(batchNumber);
	    s.setBatchMode(batchMode);
	    sr.save(s);
	}

	
	

}
