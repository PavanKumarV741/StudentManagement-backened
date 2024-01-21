package com.crud.crudappbackened.service;

import java.util.List;

import com.crud.crudappbackened.model.Student;



public interface StudentService {
	
	Student addStudent(Student student);
	
	List<Student> getStudents();
	
	Student updateStudent(Student student ,Long id);
	
	Student getStudentId(Long id);
	
	void deleteStudent(Long id);
	
	List<Student> getStudentsSortBasedOnField(String field);
	
}
