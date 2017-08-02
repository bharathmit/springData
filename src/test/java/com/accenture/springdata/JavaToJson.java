package com.accenture.springdata;



import com.accenture.springdata.entity.Address;
import com.accenture.springdata.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JavaToJson {
	
public static void main(String[] args) {
		
		try {
			Address object=new Address();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			
			//java variables
			//String json = ow.writeValueAsString(object).replaceAll(":", "").replaceAll("null", "").replaceAll(",","").replaceAll("\"", "");
			
			//Json Objject
			String json = ow.writeValueAsString(object).replaceAll("null", "\"\"");
			
			System.out.println(json);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
