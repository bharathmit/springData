package com.accenture.springdata.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	@Getter	@Setter	
	private Long authorId;
	
	@Column
	@Getter	@Setter	
	private String authorName;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="author2book",
    joinColumns=
        @JoinColumn(name="authorId", referencedColumnName="authorId"),
    inverseJoinColumns=
        @JoinColumn(name="bookId", referencedColumnName="bookId")
    )
	@Getter	@Setter	
	List<Book> books;

}
