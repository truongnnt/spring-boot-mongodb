package vn.com.vui.truongnnt.demo.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

import lombok.Data;

@QueryEntity
@Document
@Data
public class User {

	@Id
	private String userId;
	
	@Indexed(unique = true) 
	private String name;
	
	@Indexed 
	private int age;
	
	private String email;
	
	private String phone;
	
	private Date creationDate = new Date();
	private Map<String, String> userSettings = new HashMap<>();
	
}
