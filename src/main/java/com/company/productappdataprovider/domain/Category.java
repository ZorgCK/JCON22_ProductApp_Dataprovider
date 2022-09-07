package com.company.productappdataprovider.domain;

import java.util.UUID;

public class Category {

	private String uuid;
	private String categoryName;

	public Category()
	{
		
	}
	
	public Category(final String name)
	{
		this.setName(name);
	}

	public String getUuid()
	{
		return this.uuid;
	}

	public void setUuid()
	{
		this.uuid = UUID.randomUUID().toString();
	}
	
	public void readUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public String getName()
	{
		return this.categoryName;
	}

	public void setName(final String name)
	{
		this.categoryName = name;
	}
}
