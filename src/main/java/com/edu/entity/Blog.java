package com.edu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Blogs")
public class Blog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String title;
	String contents_short;
	String contents_para1;
	String contents_para2;
	String contents_para3;
	@Temporal(TemporalType.DATE)
	@Column(name = "createdate")
	Date createdate = new Date();
	String image;
	String image1;
	String image2;
	
}
