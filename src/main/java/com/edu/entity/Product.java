package com.edu.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer productid;
	String name	;
	Double price;
	String unit;
	Integer sale;
	String image;
	String image1;
	String image2;
	String image3;
	String des_short;
	String des_long;
	String information;
	boolean status;
	boolean deletehiden = false;
	
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Rate> rate;
	
	public Product(int productid) {
		this.productid = productid;
	}
}
