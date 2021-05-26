package com.ecommerce.userservice.Model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="username",nullable=false)
	@NotEmpty
	private String username;
	
	@Column(name="email",nullable=false,unique = true)
	@NotEmpty
	@Email(message = "{errors.invalid_email}")
	private String email;
	
	@Column(name="password",nullable=false)
	@NotEmpty
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
			name="users_roles",
			joinColumns = @JoinColumn(
					              name="user_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					              name="role_id",referencedColumnName = "id"))
	
	private Collection<Roles> roles;
	
	public User() {
		
	}

	public User(String username, String email, String password, Collection<Roles> roles) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
//	parameterized constructor with entity object
//	public User(User user) {
//		this.username = user.getUsername();
//		this.email = user.getEmail();
//		this.password =user.getPassword();
//		this.roles = user.getRoles();
//	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Roles> roles) {
		this.roles = roles;
	}
	
}