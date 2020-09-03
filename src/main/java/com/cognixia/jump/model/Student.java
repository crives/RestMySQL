package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


// Entity --> defines class that will model our table
// and Spring will create it using the following configurations
// with the annotations we give
@Entity
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	// @Id --> primary key
	// @GeneratedValue --> use to set auto increment for this column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// @Column --> providing definitions on how to set up our column
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "imagePath")
	private String imagePath;
	
	// unique --> unique constraint
	// nullable --> not null constraint
	@Column(unique = true, nullable = false)
	private String email;
	
	// columnDefinition --> gives a full definition of column, giving type and any constraints
	@Column(columnDefinition = "varchar(100) default 'Undecided'")
	private String department;
	
	// Default Constructor
	public Student() {
		this(-1L, "N/A", "N/A", "N/A", "N/A", "N/A");
	}

	public Student(Long id, String firstName, String lastName, String email, String department, String imagePath) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.imagePath = imagePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", imagePath=" + imagePath
				+ ", email=" + email + ", department=" + department + "]";
	}
	

}
