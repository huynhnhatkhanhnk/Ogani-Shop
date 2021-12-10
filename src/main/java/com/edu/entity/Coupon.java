package com.edu.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Coupons")
public class Coupon implements Serializable{
	@Id
	String code;
	String coupontype;
	String couponamount;
	String description;
	boolean deletehiden = false;
	@JsonIgnore
	@OneToMany(mappedBy = "coupon")
	List<Order> orders;
}
