package com.accenture.springdata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accenture.springdata.entity.Address;
import com.accenture.springdata.entity.Gender;
import com.accenture.springdata.entity.User;
import com.accenture.springdata.repo.AddressRepo;
import com.accenture.springdata.repo.UserJPARepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdataApplicationTests {
	
	@Autowired
	UserJPARepo userJPARepo;
	
	@Autowired 
	AddressRepo addressRepo;
	
	@Test
	public void insertTest() {
		
		User user=new User();
		
		try{
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date date=df.parse("1988-02-14");
			user.setDob(date);
			
			user.setEmailId("bharath@gmail.com");
			user.setFirstName("bharath");
			user.setLastName("simbu");
			user.setGender(Gender.Male);
			user.setMobile("9789944159");
			
			
			/*Address address=new Address();
			
			address.setDoorNumber("no 4/5");
			address.setLocation("Chennai");
			user.setAddress(address);
			
			addressRepo.save(address);*/
			
			userJPARepo.save(user);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	
	@Test
	public void queryMethod(){
		
		//queryMethod
		List<User> userList=userJPARepo.findByEmailId("bharath@gmail.com");
		
		for(User user: userList){
			System.out.println("User DTO : "+user.toString());
		}
		
	
	}
	
	
	
	
	
	

}
