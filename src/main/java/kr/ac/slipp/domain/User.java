package kr.ac.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, length=20, unique=true)
	private String userId;
	private String password;
	private String name;
	private String email;
	
	public User() {
		
	}
	
	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	public void update(User newUser) {
		this.userId = newUser.userId;
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
	}
}
