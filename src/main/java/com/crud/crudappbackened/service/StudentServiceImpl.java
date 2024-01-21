package com.crud.crudappbackened.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.crud.crudappbackened.exceptions.InvalidAge;
import com.crud.crudappbackened.exceptions.ResourceNotFoundException;
import com.crud.crudappbackened.exceptions.StudentAlreadyExistsException;
import com.crud.crudappbackened.model.Student;
import com.crud.crudappbackened.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository ;
	
	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	@Override
	public Student addStudent(Student student) {
		
		if(studentAlreadyExists(student.getEmail())) {
			throw new StudentAlreadyExistsException(student.getEmail()+" is alreadyExists");
		}
		if(student.getAge() <= 0) {
			throw new InvalidAge("Entered Age is Invalid");
		}
		return studentRepository.save(student);
	}

	private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email) != null;
    }

	@Override
	public Student updateStudent(Student student, Long id) {
		Student updatedStudent = studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+"not found"));
		    updatedStudent.setFirstName(student.getFirstName());
		    updatedStudent.setLastName(student.getLastName());
		    updatedStudent.setDept(student.getDept());
		    updatedStudent.setEmail(student.getEmail());
		    updatedStudent.setAge(student.getAge());
		    return studentRepository.save(updatedStudent);
		    
//		    Optional<Student> optionalStudent = studentRepository.findById(id);
//
//		    if (optionalStudent.isPresent()) {
//		        Student updatedStudent = optionalStudent.get();
//		        updatedStudent.setFirstName(student.getFirstName());
//		        updatedStudent.setLastName(student.getLastName());
//		        updatedStudent.setDept(student.getDept());
//		        updatedStudent.setEmail(student.getEmail());
//		        return studentRepository.save(updatedStudent);
//		    } else {
//		        // Handle the case where the student with the given id is not found.
//		        return null; // or throw an exception
//		    }
	}

	
	
	@Override
	public Student getStudentId(Long id) {
	    return studentRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("sorry, Student not found "));
	}
	
	@Override
	public void deleteStudent(Long id) {
	    Student student = studentRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(id + " not found"));
	    studentRepository.deleteById(id);
	    // No need to return a message here; let the controller handle the response.
	}

	@Override
	public List<Student> getStudentsSortBasedOnField(String field) {
		return studentRepository.findAll(Sort.by(Direction.ASC, field));
	}
}