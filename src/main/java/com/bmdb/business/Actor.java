package com.bmdb.business;

import java.time.LocalDate;

import javax.persistence.*;

@Entity


public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private LocalDate dob;

	public Actor() {
		super();
	}

	

	public Actor(int id, String firstName, String lastName, String gender, LocalDate dob) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
	}



	public Actor(String firstName, String lastName, String gender, LocalDate dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}



	public LocalDate getDob() {
		return dob;
	}



	public void setDob(LocalDate dob) {
		this.dob = dob;
	}



	@Override
	public String toString() {
		return "Actor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", dob=" + dob + "]";
	}


}