package com.company.productappdataprovider.controller;

import java.util.List;
import java.util.Optional;

import com.company.productappdataprovider.domain.Category;
import com.company.productappdataprovider.domain.Product;
import com.company.productappdataprovider.dto.DTOProduct;
import com.company.productappdataprovider.storage.DB;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;


@Controller("/products")
public class ProductController
{
	
	@Get
	public HttpResponse<List<Product>> getHttpList()
	{
		return HttpResponse.ok(DB.root.getProducts());
	}
	
	@Patch
	@Consumes(value = MediaType.APPLICATION_JSON)
	public HttpResponse<Product> update(DTOProduct dto)
	{
		Optional<Product> productOptional =
			DB.root.getProducts().stream().filter(p -> p.getProductUuid().equals(dto.getUuid())).findFirst();
		
		if(productOptional.isPresent())
		{
			Product productToUpdate = productOptional.get();
			
			Optional<Category> optionalCategory = DB.root.getCategories().parallelStream().filter(
				c -> c.getCategoryName().equals(dto.getCategory())).findAny();
			
			if(optionalCategory.isPresent())
			{
				productToUpdate.setCategory(optionalCategory.get());
			}
			else
			{
				Category category = new Category(dto.getCategory());
				DB.root.getCategories().add(category);
				DB.storageManager.store(DB.root.getCategories());
				
				productToUpdate.setCategory(category);
			}
			
			productToUpdate.setDescription(dto.getDescription());
			productToUpdate.setProductName(dto.getName());
			productToUpdate.setUnitPrice(dto.getUnitPrice());
			productToUpdate.setUnitsInStock(dto.getUnitsInStock());
			productToUpdate.setUnitWeight(dto.getUnitWeight());
			
			DB.storageManager.store(productToUpdate);
			
			return HttpResponse.ok(productToUpdate);
		}
		
		return HttpResponse.notFound();
	}
	
	@Put
	@Consumes(value = MediaType.APPLICATION_JSON)
	public HttpResponse<Product> insert(DTOProduct dto)
	{
		Product newProduct = new Product();
		newProduct.setDescription(dto.getDescription());
		newProduct.setProductName(dto.getName());
		newProduct.setUnitPrice(dto.getUnitPrice());
		newProduct.setUnitsInStock(dto.getUnitsInStock());
		newProduct.setUnitWeight(dto.getUnitWeight());
		
		Optional<Category> optionalCategory = DB.root.getCategories().parallelStream().filter(
			c -> c.getCategoryName().equals(dto.getCategory())).findAny();
		
		if(optionalCategory.isPresent())
		{
			newProduct.setCategory(optionalCategory.get());
		}
		else
		{
			Category category = new Category(dto.getCategory());
			DB.root.getCategories().add(category);
			DB.storageManager.store(DB.root.getCategories());
			
			newProduct.setCategory(category);
		}
		
		DB.root.getProducts().add(newProduct);
		DB.storageManager.store(DB.root.getProducts());
		
		return HttpResponse.ok(newProduct);
	}
	
	@Delete
	@Consumes(value = MediaType.ALL)
	public HttpResponse<String> delete(@QueryValue String uuid)
	{
		Optional<Product> productOptional =
			DB.root.getProducts().stream().filter(p -> p.getProductUuid().equals(uuid)).findFirst();
		
		if(productOptional.isPresent())
		{
			Product deleteProduct = productOptional.get();
			
			DB.root.getProducts().remove(deleteProduct);
			DB.storageManager.store(DB.root.getProducts());
			
			return HttpResponse.ok("Product has been successfully deleted");
		}
		
		return HttpResponse.notFound();
	}
	
}
