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
		
		/*1.cascade=CascadeType.ALL,fetch=FetchType.EAGER
			    insert,find,delete ----> using FetchType
		  2.ModelMappere Usecase when You use FetchType lazy loading   
		  3.Add properties --spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true

		4.optional=true,orphanRemoval=true
		    insert,find,delete ----> using both value
		
		5.left outer and inner outer join when you use optional*/
		
		
		try{
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date date=df.parse("1988-02-14");
			user.setDob(date);
			
			user.setEmailId("bharath@gmail.com");
			user.setFirstName("Bharath");
			user.setLastName("simbu");
			user.setGender(Gender.Male);
			user.setMobile("9789944159");
			
			
			Address address=new Address();
			
			address.setDoorNumber("no 4/5");
			address.setLocation("Chennai");
			user.setAddress(address);
			
			//addressRepo.save(address);
			
			userJPARepo.save(user);
			
			System.out.println("================Find By User Entity Call ==================");
			User userObj=userJPARepo.findOne(user.getUserId());
			
			/*System.out.println("Property expressions Call");
			userJPARepo.findByAddress(address);*/
			
			System.out.println("==============Junit User Data print Call ==================");
			System.out.println("User Data" + userObj.toString());
			
			System.out.println("===============Delete User Data print Call ==================");
			userJPARepo.delete(user);
			
			
			/*address.setUser(user);
			addressRepo.save(address);
			
			System.out.println("Find By Address Entity Call ==================");
			addressRepo.findOne(address.getAddressId());
			
			System.out.println("Delete Address Data print Call ==================");
			addressRepo.delete(address);*/
			
			
			
			
			
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
