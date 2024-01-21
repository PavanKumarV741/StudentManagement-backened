package com.crud.crudappbackened.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudappbackened.model.Student;
import com.crud.crudappbackened.service.StudentServiceImpl;


@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
	
	@Autowired
	public StudentServiceImpl service;
	
	@GetMapping("/get")
	public ResponseEntity<List<Student>> getStudents() {
	    List<Student> students = service.getStudents();
	    return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public Student addStudent(@RequestBody Student student) {
		Student s = service.addStudent(student);
		return s;
	} 
	
	@PutMapping("/update/{id}")
	public Student updateStudent(@RequestBody Student student,@PathVariable Long id) {
		return service.updateStudent(student, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable Long id) {
		service.deleteStudent(id);
	}
	
	@GetMapping("/getById/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return service.getStudentId(id);
	}
	
	@GetMapping("/sortByField/{field}")
	public List<Student> getStudentsSortBasedOnField(@PathVariable String field){
		return service.getStudentsSortBasedOnField(field);
	}
	
}

