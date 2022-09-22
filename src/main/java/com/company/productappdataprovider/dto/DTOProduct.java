package com.company.productappdataprovider.dto;

import java.math.BigDecimal;
import java.util.UUID;


public class DTOProduct
{
	private String		uuid	= UUID.randomUUID().toString();
	private String		name;
	private String		description;
	private String		category;
	private BigDecimal	unitPrice;
	private double		unitWeight;
	private int			unitsInStock;
	
	
	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public BigDecimal getUnitPrice()
	{
		return unitPrice;
	}
	
	public void setUnitPrice(BigDecimal unitPrice)
	{
		this.unitPrice = unitPrice;
	}
	
	public double getUnitWeight()
	{
		return unitWeight;
	}
	
	public void setUnitWeight(double unitWeight)
	{
		this.unitWeight = unitWeight;
	}
	
	public int getUnitsInStock()
	{
		return unitsInStock;
	}
	
	public void setUnitsInStock(int unitsInStock)
	{
		this.unitsInStock = unitsInStock;
	}
	
}
