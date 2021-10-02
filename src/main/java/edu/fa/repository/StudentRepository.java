package edu.fa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.fa.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	 Student findByNameAndLocation(String name ,String location);
	 
	 
}
