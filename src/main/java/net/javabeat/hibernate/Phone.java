package net.javabeat.hibernate;

import java.io.Serializable;

public class Phone implements Serializable {
	private Long id;
	private String number;
	private Person person;
	
	public Phone(){
		
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Phone(String number, Person person){
		this.number = number;
		this.person = person;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
