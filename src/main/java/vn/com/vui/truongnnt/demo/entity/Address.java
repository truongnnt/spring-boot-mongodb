package vn.com.vui.truongnnt.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "address")
@Data
public class Address {

	@Id
	private String id;

	private String location;
	private String state;
	private String country;
}
