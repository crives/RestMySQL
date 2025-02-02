package com.cognixia.jump.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Student;
import com.cognixia.jump.repository.StudentRepository;

@RequestMapping("/api")
@RestController
public class StudentController {
	
	// will do CRUD operations
	// Autowired enables it to be implemented and set up for you
	// once set up Spring app
	@Autowired
	StudentRepository service;
	
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		
		return service.findAll();
		
	}
	
	
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable long id) {
		
		Optional<Student> studentOpt = service.findById(id);
		
		if(studentOpt.isPresent()) {
			return studentOpt.get();
			
		}
		
		return new Student();
	}

	
	
	@PostMapping("/add/student")
	public void addStudent(@RequestBody Student newStudent) {
		
		// to know that nobody should have this Id
		// check before you update a student to know
		// we are properly doing an add
		newStudent.setId(-1L);
		
		// makes sure new student is added in
		// returns student
		Student added = service.save(newStudent);
		System.out.println("Added: " + added);
	}

	@PutMapping("/update/student")
	public String updateStudent(@RequestBody Student updateStudent) {
		
		// check if student exists, then update them
		
		Optional<Student> found = service.findById(updateStudent.getId());
		
		if(found.isPresent()) {
			service.save(updateStudent);
			return "Saved: " + updateStudent.toString();
		} else {
			return "Could not update student, the id = " + updateStudent.getId()
				+ " doesn't exist";
		}
	}

	@PatchMapping("/update/student/department")
	public @ResponseBody String updateDepartment(@RequestBody Map<String, String> deptInfo) {
		
		// will need to parse because will still return a string
		long id = Long.parseLong(deptInfo.get("id") );
		String department  = deptInfo.get("department");
		
		// will return back Optional
		Optional<Student> found = service.findById(id);
		
		if(found.isPresent()) {
			
			Student toUpdate = found.get();
			
			String old = toUpdate.getDepartment();
			
			toUpdate.setDepartment(department);
			
			// save new department to be able to update it
			service.save(toUpdate);
			
			return "Old Department: " + old + "\nNew Department: " 
					+ department;
		} else {
			return "Could not update department, student with id = " 
					+ id + " doesn't exist";
			
		}
		
	}
	
	@DeleteMapping("/delete/student/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable long id) {
		
		Optional<Student> found = service.findById(id);
		
		if(found.isPresent()) {
			
			service.deleteById(id);

			return ResponseEntity.status(200).body("Deleted student with id = "
					+ id);
		} else {
			return ResponseEntity.status(400)
					.header("student id", id + "")
					.body("Student with id = " + id + " not found");
		}
		
		
	}
	
	
}

