package com.ecommerce.cartservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="role")
public class Roles {
	
	//if we donot use name in column and table annotation then by default it will use names of model class
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="rolename",nullable = false,unique=true) 
	@NotEmpty
	private String rolename;

	public Roles(String rolename) {
		super();
		this.rolename = rolename;
	}
	
	public Roles() {
			
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}