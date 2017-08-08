package com.accenture.springdata.repo;

import org.springframework.beans.factory.annotation.Value;

public interface NoAddresses {

	String getFirstName();

	String getLastName();
	
	@Value("#{target.firstName} #{target.lastName}")
	String getFullName();


}
