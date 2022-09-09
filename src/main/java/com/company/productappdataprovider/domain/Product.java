package com.company.productappdataprovider.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
	private String uuid;
	private String name, description;
	private Category category;
	private BigDecimal unitPrice;
	private double unitWeight;
	private int unitsInStock;

	private String imageName;
	private byte[] imageBytes;

	public Product()
	{

	}

	public Product(final String name, final String desc, final Category category, final BigDecimal price,
			final double weight, final int stock)
	{
		this.setProductName(name);
		this.setDescription(desc);
		this.setCategory(category);
		this.setUnitPrice(price);
		this.setUnitWeight(weight);
		this.setUnitsInStock(stock);
	}

	public void setProductUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public String getProductUuid()
	{
		return this.uuid;
	}
	
	public void newUuid()
	{
		this.uuid = UUID.randomUUID().toString();
	}

	public String getProductName()
	{
		return this.name;
	}

	public void setProductName(final String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(final BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getUnitWeight() {
		return this.unitWeight;
	}

	public void setUnitWeight(final Double unitWeight) {
		this.unitWeight = unitWeight;
	}

	public int getUnitsInStock() {
		return this.unitsInStock;
	}

	public void setUnitsInStock(final int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(final String imageName) {
		this.imageName = imageName;
	}

	public byte[] getImageBytes() {
		return this.imageBytes;
	}

	public void setImageBytes(final byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}
}
