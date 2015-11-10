package net.javabeat.hibernate;

import java.io.Serializable;
import java.util.List;

/**
 * Model class for Person
 */
public class Person implements Serializable {
	private Long personId;
	private String name;

	private Address address;
	private List<Phone> phones;

	public Person() {
	}
	
	public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}