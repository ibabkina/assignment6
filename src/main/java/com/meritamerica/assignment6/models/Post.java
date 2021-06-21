package com.meritamerica.assignment6.models;

import javax.validation.constraints.NotBlank;


/**
 * This class is not related this Bank project. It's just an Spring Boot example 
 * 
 * @author Irina Babkina 
 */

public class Post {
	static int nextId = 0;
	int id;
	String title;
	
	@NotBlank(message = "Body is mandatory") //Can validate whatever you want, look in package
	String body;
	
	public Post() {
		this.id = nextId++;
		this.title = "";
		this.body = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	

}
