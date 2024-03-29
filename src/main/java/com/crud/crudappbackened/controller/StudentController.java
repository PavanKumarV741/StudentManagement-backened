package com.crud.crudappbackened.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<Student> save(@RequestParam MultipartFile file,
					@RequestParam String firstName,
					@RequestParam String lastName,
					@RequestParam int age,
					@RequestParam String email,
					@RequestParam String dept
						) throws IOException {
		Student s = new Student();
		s.setImageData(file.getBytes());
		s.setFirstName(firstName);
		s.setLastName(lastName);
		s.setAge(age);
		s.setEmail(email);
		s.setDept(dept);
		s.setFileName(file.getOriginalFilename());
		
		Student res = service.addStudent(s);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
//	@PostMapping("/add")
//	public Student addStudent(@RequestParam MultipartFile file,
//							@RequestParam String firstName,
//							@RequestParam String lastName,
//							@RequestParam int age,
//							@RequestParam String email,
//							@RequestParam String dept
//							) throws IOException {
//		String uuid = UUID.randomUUID().toString().substring(0,6);
//		final String FOLDER_PATH = "src\\main\\resources\\static\\uploads\\";
//		String fileName = uuid +"_"+ file.getOriginalFilename();
//		final String FILE_PATH = FOLDER_PATH + fileName;
//		
//		
//		 // Create directory if it doesn't exist
////	    Files.createDirectories(Paths.get(FOLDER_PATH));
//		
//		if(!file.isEmpty()) {
//			byte[] bytes = file.getBytes();
//			Files.write(Paths.get(FILE_PATH),bytes);
//		}
//		Student student = new Student(firstName,lastName,age,email,dept,fileName,FILE_PATH);
//		Student s = service.addStudent(student);
//		return s;
//	} 
	
//	@PutMapping("/update/{id}")
//	public Student updateStudent(@RequestBody Student student,@PathVariable Long id) {
//		return service.updateStudent(student, id);
//	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Student> update(@PathVariable Long id,
	                    @RequestParam(required = false) MultipartFile file,
	                    @RequestParam(required = false) String firstName,
	                    @RequestParam(required = false) String lastName,
	                    @RequestParam(required = false) Integer age,
	                    @RequestParam(required = false) String email,
	                    @RequestParam(required = false) String dept) throws IOException {

	    // Retrieve the existing student record from the database
	    Student existingStudent = service.getStudentId(id);
	    if (existingStudent == null) {
	        // If student with the given ID doesn't exist, return 404 Not Found
	        return ResponseEntity.notFound().build();
	    }

	    // Update the attributes of the existing student if new values are provided
	    if (file != null) {
	        existingStudent.setImageData(file.getBytes());
	        existingStudent.setFileName(file.getOriginalFilename());
	    }
	    if (firstName != null) {
	        existingStudent.setFirstName(firstName);
	    }
	    if (lastName != null) {
	        existingStudent.setLastName(lastName);
	    }
	    if (age != null) {
	        existingStudent.setAge(age);
	    }
	    if (email != null) {
	        existingStudent.setEmail(email);
	    }
	    if (dept != null) {
	        existingStudent.setDept(dept);
	    }

	    // Call the service method to update the student record
	    Student updatedStudent = service.updateStudent(existingStudent, id);

	    // Return the updated student record in the response body
	    return ResponseEntity.ok(updatedStudent);
	}

	
	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable Long id) {
		service.deleteStudent(id);
	}
	
//	@GetMapping("/getById/{id}")
//	public Student getStudentById(@PathVariable Long id) {
//		return service.getStudentId(id);
//	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
	    Student student = service.getStudentId(id);
	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	            .body(student);
	}

	
	@GetMapping("/sortByField/{field}")
	public List<Student> getStudentsSortBasedOnField(@PathVariable String field){
		return service.getStudentsSortBasedOnField(field);
	}
	
//	@GetMapping("/getByFileName/{fileName}")
//	public ResponseEntity<Student> getStudentByFileName(@PathVariable String fileName) {
//	    Student student = service.getStudentByFileName(fileName);
//	    if (student != null) {
//	        return ResponseEntity.ok()
//	                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//	                .body(student);
//	    } else {
//	        return ResponseEntity.notFound().build();
//	    }
//	}
	
//	@GetMapping("/getByFileName/{fileName}")
//	public ResponseEntity<byte[]> getStudentByFileName(@PathVariable String fileName) {
//		byte[] res = service.getStudentByFileName(fileName);
//		
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(res);
//	}
	
	@GetMapping("/getByFileName/{fileName}")
    public ResponseEntity<String> getImageDataByFileName(@PathVariable String fileName) {
        try {
            byte[] imageData = service.getStudentByFileName(fileName);
            // Set the appropriate content type header
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity(imageData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving image data: " + e.getMessage());
        }
    }

}

