package com.edu.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Contacts")
public class Contact implements Serializable {

	@Id
	String id;
	String name;
	String email;
	String contents;
	String topic;
}
