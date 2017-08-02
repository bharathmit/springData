package com.accenture.springdata.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(callSuper=true, includeFieldNames=true)
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	@Getter	@Setter	
	private Long addressId;
	
	@Column
	@Getter	@Setter	
	private String doorNumber;
	
	@Column
	@Getter	@Setter	
	private String location;

}
