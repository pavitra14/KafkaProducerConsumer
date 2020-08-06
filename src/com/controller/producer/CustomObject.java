package com.controller.producer;


public class CustomObject {
	private int id;
	private String name;
	private String email;
	
	public CustomObject()
	{
		this.id = 1;
		this.name = "default";
		this.email = "john@doe.com";
	}
	
	public CustomObject(int id, String name, String email)
	{
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public int getId()
	{
		return this.id;
	}
	public void setId(int i)
	{
		this.id = i;
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String n)
	{
		this.name = n;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	public void setEmail(String e)
	{
		this.email = e;
	}
	
	@Override
	public String toString()
	{
		return "CustomObject{" + 
				"id='" + this.id + "'" +
				", name='" + this.name + "'" + 
				", email='" + this.email + "'" + 
				"}";
	}

}
