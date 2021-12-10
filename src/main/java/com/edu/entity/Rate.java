package com.edu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Rates")
public class Rate implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Rate_id")
	Integer rateId;
	
	String comment;
	Double star;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Create_date")
	Date createDate = new Date();
	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "createDate")
//	Date createDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "Product_id")
	Product product;
	
	@ManyToOne
	@JoinColumn(name = "Username")
	Account account;

	public void setRate(Date createDate) {
		this.createDate = createDate;
	}
	
}
