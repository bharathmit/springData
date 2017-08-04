package com.accenture.springdata.controller;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import lombok.extern.log4j.Log4j;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.springdata.entity.Address;
import com.accenture.springdata.entity.User;
import com.accenture.springdata.repo.UserJPARepo;


@RestController
@RequestMapping("/user")
@Log4j
public class UserController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	UserJPARepo userJpa;
	
	/** CRUD OPERATON **/
	@RequestMapping(method=RequestMethod.POST)
	public User saveUser(@RequestBody User reqObject){
		log.info("user save method");
		return userJpa.saveAndFlush(reqObject);		
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public User updateUser(@RequestBody User reqObject){
		log.info("user update method");
		return userJpa.saveAndFlush(reqObject);		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> getUser(){
		return userJpa.findAll();	
	}
	
	//http://localhost:8080/user?userId=2
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@RequestParam("userId") final Long userId){
		userJpa.delete(userId);
		return new ResponseEntity<String>("User deleted from table", HttpStatus.OK);		
	}
	
	
	
	/** Query Method **/
	
	//http://localhost:8080/user/queryMethod?email=bharath@gmail.com
	@RequestMapping(value="/queryMethod",method=RequestMethod.GET)
	public List<User> queryMethod(@RequestParam("email") final String email){
		log.info("queryMethodByEmailId {}"+email);
		return userJpa.findByEmailId(email);
	}
	
	//http://localhost:8080/user/propertyExpressions?Location=chennai
	@RequestMapping(value="/propertyExpressions",method=RequestMethod.GET)
	public List<User> propertyExpressions(@RequestParam("location") final String Location){
		log.info("propertyExpressions {}"+Location);
		
		return userJpa.findByAddressLocation(Location);
	}
	
	
	//http://localhost:8080/user/specialParameter?page=0&size=10&firstName=bharath
	@RequestMapping(value="/specialParameter",method=RequestMethod.GET)
	public List<User> specialParameter(@RequestParam("page") final int page, @RequestParam("size") final int size, @RequestParam("firstName") final String firstName){
		PageRequest pageable=new PageRequest(page, size, Direction.ASC, "firstName");
		log.info("specialParameter {}"+firstName);
		return userJpa.findByFirstName(firstName,pageable);
	}
	
	
	//http://localhost:8080/user/limitingQuery?zipCode=1l
	@RequestMapping(value="/limitingQuery",method=RequestMethod.GET)
	public List<User> limitingQuery(){
		log.info("limitingQuery {}");
		return userJpa.findFirst5ByOrderByLastNameAsc();
	}
	
	//http://localhost:8080/user/streaming
	@RequestMapping(value="/streaming",method=RequestMethod.GET)
	@Transactional
	public List<User> streaming(){
		log.info("streaming {}");
		List<User> list;
		try (Stream<User> stream = userJpa.readAllByFirstNameNotNull().parallel()) {
			list= stream.collect(Collectors.toList());
		  } 
		return list;
	}
	
	//http://localhost:8080/user/asyncQuery?firstName=bharath
	@RequestMapping(value="/asyncQuery",method=RequestMethod.GET)
	public Future<User> asyncQuery( @RequestParam("firstName") final String firstName){
		log.info("asyncQuery {}"+firstName);
		return userJpa.findByFirstName(firstName);
	}
	
	
	
	/** Custom Query **/
	
	
	//http://localhost:8080/user/namedQuery?email=bharath@gmail.com
	@RequestMapping(value="/namedQuery",method=RequestMethod.GET)
	public List<User> namedQueryByEmailId(@RequestParam("email") final String email){
		log.info("namedQueryByEmailId {}"+email);
		return userJpa.findByNamedQuery(email);
	}
	
	//http://localhost:8080/user/query?email=bharath@gmail.com
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public List<User> QueryByEmailId(@RequestParam("email") final String email){
		log.info("namedQueryByEmailId {}"+email);
		return userJpa.findByQuery(email);
	}
	
	
	//http://localhost:8080/user/nativeQuery?email=bharath@gmail.com
	@RequestMapping(value="/nativeQuery",method=RequestMethod.GET)
	public List<User> nativeQueryByEmail(@RequestParam("email") final String email){
		log.info("nativeQueryByEmail {}"+email);
		return userJpa.nativeQueryByEmail(email);
	}
	
	
	//http://localhost:8080/user/modifying?email=simbu@gmail.com
	@RequestMapping(value="/modifying",method=RequestMethod.GET)
	public ResponseEntity<String> updateByEmail(@RequestParam("email") final String email ){
		 int row=userJpa.updateEMail(email);
		return new ResponseEntity<String>("Email update count"+row, HttpStatus.OK);	
	}
	
	
	@RequestMapping(value="/criteriaSearch",method=RequestMethod.POST)
	public List<User> searchByUser(@RequestBody User reqObject){
		
		Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(User.class);
        
        if(reqObject.getUserId() !=null && reqObject.getUserId() >0l){
    		criteria.add(Restrictions.eq("userId", reqObject.getUserId()));
    	}
    
    	
        /*criteria.setCacheable(true);
		criteria.setCacheRegion("query.searchByUser");*/
    	
    	/** Pagination Start*/
		int count = criteria.list().size();
		
		criteria.setFirstResult((reqObject.getPage() - 1) * reqObject.getRecordePerPage());
		criteria.setMaxResults(reqObject.getRecordePerPage());
		
		
		List<User> list = criteria.list();
		
		/** Pagination End*/
		
        return list;
        
	}
	
	
	

}
