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
		this.setCategoryName(name);
	}

	public String getCategoryUuid()
	{
		return this.uuid;
	}

	public void setCategoryUuid(String uuid)
	{
		this.uuid = uuid;
	}
	
	public void newUuid()
	{
		this.uuid = UUID.randomUUID().toString();
	}

	public String getCategoryName()
	{
		return this.categoryName;
	}

	public void setCategoryName(final String name)
	{
		this.categoryName = name;
	}
}
