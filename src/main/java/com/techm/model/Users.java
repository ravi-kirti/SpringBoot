package com.techm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="city")
	private String city;
	
	@Column(name="zipcode")
	private int zipcode;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="rollname")
	private String rollname;

	public Users() {
		
	}
	
	
	public Users(String name, String username, String password, String email, String city, int zipcode, String phone,
			String rollname) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.city = city;
		this.zipcode = zipcode;
		this.phone = phone;
		this.rollname = rollname;
	}


	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRollname() {
		return rollname;
	}

	public void setRollname(String rollname) {
		this.rollname = rollname;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
				+ email + ", city=" + city + ", zipcode=" + zipcode + ", phone=" + phone + ", rollname=" + rollname
				+ "]";
	}
	
	
	
	
}
