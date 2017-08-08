package com.accenture.springdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.accenture.springdata.entity.Address;
import com.accenture.springdata.entity.Author;
import com.accenture.springdata.entity.Book;
import com.accenture.springdata.entity.Gender;
import com.accenture.springdata.entity.Role;
import com.accenture.springdata.entity.User;
import com.accenture.springdata.repo.AddressRepo;
import com.accenture.springdata.repo.AuthorRepo;
import com.accenture.springdata.repo.NoAddresses;
import com.accenture.springdata.repo.UserJPARepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdataApplicationTests {
	
	@Autowired
	UserJPARepo userJPARepo;
	
	@Autowired 
	AddressRepo addressRepo;
	
	
	@Autowired
	AuthorRepo authorRepo;
	
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	public void insertTest() {
		
		User user=new User();
		
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
			address.setUser(user);
			user.setAddress(address);
			
			
			List<Role> roles=new ArrayList<Role>();
			
			Role role1=new Role(); 
			role1.setRoleName("Admin");
			role1.setUser(user);			
			roles.add(role1);
			
			Role role2=new Role(); 
			role2.setRoleName("Manager");
			role2.setUser(user);
			
			roles.add(role2);
			
			user.setRoles(roles);
			
			userJPARepo.save(user);
			
			System.out.println("================Find By User Entity Call ==================");
			userJPARepo.findOne(user.getUserId());
			
			System.out.println("==============Property expressions Call");
			
			userJPARepo.findByAddressLocation("Chennai");
			
			
			
			
			System.out.println("===============Delete User Data print Call ==================");
			//userJPARepo.delete(user);
			
			
			/** Bio Direction Example **/ 
			
			/*
			//address.setUser(user);
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
	public void manyToManyTest(){
		
		Author author=new Author();
		List<Author> authorList=new ArrayList<Author>();
		List<Book> booklist=new ArrayList<Book>();
		
		author.setAuthorName("Bharath");
		
		authorList.add(author);
		
		
		Book book1=new Book();
		book1.setBookName("Spring");		
		book1.setAuthors(authorList);
		
		booklist.add(book1);
		
		Book book2=new Book();
		book2.setBookName("Hibernate");
		book1.setAuthors(authorList);
		booklist.add(book2);
		
		
		author.setBooks(booklist);
		
		authorRepo.save(author);
		
	}
	
	
	@Test
	public void queryMethod(){
		
		/*System.out.println("JPA Query Method Call ====================");
		userJPARepo.findByEmailId("bharath@gmail.com");
		
		System.out.println("NamedQuery Call ====================");
		userJPARepo.findByNamedQuery("bharath@gmail.com");
		
		System.out.println("NativeQuery Call ====================");
		userJPARepo.nativeQueryByEmail("bharath@gmail.com");
		
		System.out.println("HQL Call ====================");
		userJPARepo.findByQuery("bharath@gmail.com");
		
		System.out.println("Named Parameter Call ====================");
		userJPARepo.parameterQuery("bharath@gmail.com");
		
		System.out.println("Modifying Query Call ====================");
		userJPARepo.updateEMail("simbu@gmail.com");*/
		
		
		System.out.println("Projections Query Call ====================");
		NoAddresses address=userJPARepo.findByLastName("simbu");		
		System.out.println(address.getFirstName());
		System.out.println(address.getLastName());
		System.out.println(address.getFullName());
	
	}
	
	
	@Test
	@Transactional
	public void criteriaTest(){
		User reqObject=new User();
		reqObject.setEmailId("simbu@gmail.com");
		
		Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(User.class);        
        
        if(!StringUtils.isEmpty(reqObject.getEmailId())){
    		criteria.add(Restrictions.eq("emailId", reqObject.getEmailId()));
    	}
        
        List<User> list = criteria.list();
    	
    	
		
	}
	
	
	
	@Test
	@Transactional
	public void hqlTest(){
		
		//Select
		Session session = entityManager.unwrap(Session.class);
		Query query=session.createQuery("FROM User u");
		List results = query.list();
    			
		//Update & delete using query.executeUpdate().
		
	}
	
	
	
	
	

}
