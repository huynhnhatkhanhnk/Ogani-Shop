package com.edu.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {

	@Id
	String username;
	String password;
	@NotBlank(message = "Name is mandatory")
	String name;
	boolean gender;
	@Temporal(TemporalType.DATE)
	@Column(name = "Birthday")
	Date birthday = new Date();
	String address;
	String email;
	String phone;
	String image;
	boolean deletehiden = false;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Rate> rate;
		
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	@Column(name = "reset_token")
    private String resetToken;
	
	public Account(String username) {
		this.username = username;
	}
}
