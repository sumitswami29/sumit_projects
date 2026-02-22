package edu.cjc.sma.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.sma.app.model.Student;
import edu.cjc.sma.app.servicei.StudentServiceI;

@Controller
public class AdminController {
	@Autowired
	StudentServiceI ssi;

	@RequestMapping("/")
	public String preLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String loginStudent(@RequestParam("username") String username,
			@RequestParam("password") String password,Model m) {
		
		 if(username.equals("Admin") && password.equals("Admin"))
		 {
			 List<Student> list=ssi.paging(0,2);
			 m.addAttribute("data", list);
			 return "adminscreen";
		 }else {
			 m.addAttribute("login_fail","Enter valid login details");
			 return "login";
		 }
		
	}
	
	@RequestMapping("/enroll_student")
	public String saveData(@ModelAttribute Student s,Model m) {
		
		ssi.saveData(s);
		
	List<Student> list=ssi.getAllStudents();
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	
	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("id") int studentId,Model m)
	{
	
		ssi.removeStudent(studentId);
		List<Student> list=ssi.getAllStudents();
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	@RequestMapping("/search")
	public String searchByBatchStudents(@RequestParam("batchNumber") String batchNumber,Model m) {
		
		List<Student> list=ssi.getStudentsByBatch(batchNumber);
		 if(list.size()>0) {
			 m.addAttribute("data", list);
		 }else {
			 List<Student> list1=ssi.getAllStudents();
			 m.addAttribute("data", list1);
			 m.addAttribute("message","No record found for this batch "+batchNumber);
		 }
	
		return "adminscreen";
	}
	
	@RequestMapping("/paging")
	public String paging(@RequestParam("pageNo") int pageNo,Model m) {
		int size=2;
		     List<Student> list   = ssi.paging(pageNo,size);
	
		     
		     m.addAttribute("data", list);
		
		return "adminscreen";
		
	}
	
	
	@RequestMapping("/fees")
	public String onFees(@RequestParam("id") int studentId,Model m) {
		
 Student st=ssi.getSingleStudent(studentId);
		m.addAttribute("st", st);
		return "fees";
	}
	
	@RequestMapping("/payfees")
	public String payFees(@RequestParam("studentid") int studentId,@RequestParam("ammount") double ammount,Model m) {
		
		ssi.updateStudentFees(studentId,ammount);
		
		List<Student> list=ssi.getAllStudents();
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	@RequestMapping("/shiftBatch")
	public String shiftBatch(@RequestParam("id") int studentId, Model m) {
	    Student st = ssi.getSingleStudent(studentId);
	    m.addAttribute("student", st);
	    return "shiftbatch";
	}

	@PostMapping("/updateBatch")
	public String updateBatch(
	        @RequestParam int studentId,
	        @RequestParam String batchNumber,
	        @RequestParam String batchMode,
	        Model m) {

	    ssi.updateStudentBatch(studentId, batchNumber, batchMode);

	    List<Student> list = ssi.getAllStudents();
	    m.addAttribute("data", list);

	    return "adminscreen";
	}


	
	
	
	
}
