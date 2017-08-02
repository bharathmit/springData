package com.accenture.springdata.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@NamedQuery(name = "User.findByNamedQuery",query = "select u from User u where u.emailId = ?1")
@Table(name="user")
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(callSuper=true, includeFieldNames=true)
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	@Getter	@Setter	
	private Long userId;
	
	
	@Column(nullable=false,unique=true)
	@Getter	@Setter	
	private String firstName;
	
	@Column(nullable=false)
	@Getter	@Setter	
	private String lastName;
	
	@Column
	@Getter	@Setter	
	private String mobile;
	
	@Column
	@Getter	@Setter	
	private String emailId;
	
	@Temporal( TemporalType.DATE)
	@DateTimeFormat(style = "yyyy-MM-dd")
	@Column
	@Getter	@Setter	
	private Date dob;
	
	@Column
	@Enumerated(EnumType.STRING)
	@Getter	@Setter	
	private Gender gender;
	
	
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="addressId")
	@Getter	@Setter	
	@JsonIgnore
	private Address address;
	
	
	
	
	
	
	@Transient 
	@Getter	@Setter	
	private int page;
	
	@Transient 
	@Getter	@Setter	
	private int recordePerPage;
	
	
	
	
	
	
	
	
}
