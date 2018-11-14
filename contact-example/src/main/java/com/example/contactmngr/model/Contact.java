package com.example.contactmngr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private Long phone;
	
	// Create default constructor for JPA
	public Contact(){}
	
	public Contact(ContactBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.phone = builder.phone;
	}
		
	@Override
	public String toString() {
		return "Contact {userId=" + userId + 
				", firstName=" + firstName + 
				", lastName=" + lastName + 
				", email=" + email
				+ ", phone=" + phone + 
				"}";
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	public static class ContactBuilder {
		private String firstName;
		private String lastName;
		private String email;
		private Long phone;
		
		public ContactBuilder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		
		public ContactBuilder withEmail(String email) {
			this.email = email;
			return this;
		}
		
		public ContactBuilder withPhone(Long phone) {
			this.phone = phone;
			return this;
		}
		
		public Contact build() {
			return new Contact(this);
		}
	}

}
