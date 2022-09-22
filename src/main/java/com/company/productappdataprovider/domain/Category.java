package com.company.productappdataprovider.domain;

import java.util.UUID;

public class Category {

	private String uuid = UUID.randomUUID().toString();
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

	public String getCategoryName()
	{
		return this.categoryName;
	}

	public void setCategoryName(final String name)
	{
		this.categoryName = name;
	}
}
