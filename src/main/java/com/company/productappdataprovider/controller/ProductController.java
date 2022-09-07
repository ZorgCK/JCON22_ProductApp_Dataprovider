package com.company.productappdataprovider.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.company.productappdataprovider.domain.Category;
import com.company.productappdataprovider.domain.Product;
import com.company.productappdataprovider.storage.DB;
import com.company.productappdataprovider.utils.MockupUtils;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import one.microstream.persistence.types.Storer;

@Controller("/prodcuts")
public class ProductController {

	@Get("/create")
	public HttpResponse<?> createProducts()
	{
		List<Product> allCreatedProducts = MockupUtils.loadMockupData();
		
		DB.root.getProducts().addAll(allCreatedProducts);
		DB.storageManager.store(DB.root.getProducts());
		
		return HttpResponse.ok("Products successfully created!");
	}
	
	@Get("/createSingle")
	public HttpResponse<?> createSingleProduct()
	{
		Category category = new Category("Car");
		Product product = new Product("SUV", "built in 2021 by <random car company>",
				category, new BigDecimal(49812.31), 2817,
				6);
		
		DB.root.getProducts().add(product);
		DB.storageManager.store(DB.root.getProducts());
		
		return HttpResponse.ok("Product successfully created!");
	}
	
	@Get
	public List<Product> getProduct()
	{
		return DB.root.getProducts();
	}
	
	@Get("/startsWith_A")
	public List<Product> getProductsWithA()
	{
		return DB.root.getProducts().stream().filter(product -> product.getName().startsWith("A")).collect(Collectors.toList());
	}
	
	@Get("/clear")
	public HttpResponse<?> clearProducts()
	{
		DB.root.getProducts().clear();
		DB.storageManager.store(DB.root.getProducts());
		
		return HttpResponse.ok("Products successfully cleared!");
	}
	
//	@Get("/updateSingle")
//	public HttpResponse<?> updateSingleProduct()
//	{
//		Product product = DB.root.getProducts().stream().findFirst().get();
//		DB.storageManager.store(product);
//		
//		return HttpResponse.ok("Product successfully updated!");
//	}
	
	@Get("/updateMulti")
	public HttpResponse<?> updateMultiProducts()
	{
		Storer es = DB.storageManager.createEagerStorer();
		
		DB.root.getProducts().stream().filter(product -> product.getName().startsWith("A")).forEach(product ->
		{
			BigDecimal value = product.getUnitPrice().multiply(new BigDecimal(0.9));
			product.setUnitPrice(value);
			
			es.store(es);
		});
		
		es.commit();
		
		return HttpResponse.ok("Products successfully updated!");
	}
	
}
