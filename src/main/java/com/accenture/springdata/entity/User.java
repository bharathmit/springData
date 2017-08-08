package com.accenture.springdata.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
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
	
	
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Getter	@Setter	
	@JsonIgnore
	private Address address;
	
	
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Getter	@Setter	
	private List<Role> roles;
	
	
	
	
	
	@Transient 
	@Getter	@Setter	
	private int page;
	
	@Transient 
	@Getter	@Setter	
	private int recordePerPage;
	
	
	
	/*@PrePersist
	public void prePersist() {
		System.out.println("before a new entity is persisted (added to the EntityManager)");
	}
	
	@PostPersist
	public void postPersist() {
		System.out.println("after storing a new entity in the database (during commit or flush)");
	}
	@PostLoad
	public void postLoad() {
		System.out.println("after an entity has been retrieved from the database");
	}
	@PreUpdate
	public void PreUpdate() {
		System.out.println("when an entity is identified as modified by the EntityManager");
	}
	@PostUpdate
	public void PostUpdate() {
		System.out.println("after updating an entity in the database (during commit or flush)");
	}
	@PreRemove
	public void PreRemove() {
		System.out.println("when an entity is marked for removal in the EntityManager");
	}
	@PostRemove
	public void PostRemove() {
		System.out.println("after deleting an entity from the database (during commit or flush)");
	}*/

	
	
	
	
	
	
	
	
}
