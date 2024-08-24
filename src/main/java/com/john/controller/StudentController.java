package com.john.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.john.entities.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
	
	public List<Student> students=new ArrayList<Student>(List.of(
			new Student(1,"vinod","JavaFullstackDeveloper"),
			new Student(2,"vonzy","Actor"),
			new Student(3,"nancy","kpop")
			));
	
	@GetMapping("/students")
	public List<Student> getStudent(){
		return students;
	}
	@PostMapping("/student")
	public Student CreateStudent(@RequestBody Student student){
		 students.add(student);
		 return student;
	}
	@GetMapping("/token")
	public CsrfToken getToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}

}
