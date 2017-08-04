package com.accenture.springdata.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.springdata.entity.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

}
