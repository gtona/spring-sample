package com.mycompany.ppms.models;

import java.io.Serializable;

public class Product implements Serializable {
	private final String id;
	private String name;

	public Product(String id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getId() {return id;}
	public String getName() {return name;}

	public void setName(String name) {
		this.name = name;
	}

	public String toJson() {
		return "{\"" +
				  "id\":\"" + this.id + "\"," +
				  "\"name\":\"" + this.name + "\"" +
				"}";
	}
}
