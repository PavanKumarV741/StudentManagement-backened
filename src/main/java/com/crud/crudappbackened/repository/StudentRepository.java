package com.crud.crudappbackened.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.crudappbackened.model.Student;



public interface StudentRepository extends JpaRepository<Student, Long>{

	Student findByEmail(String email);

}
