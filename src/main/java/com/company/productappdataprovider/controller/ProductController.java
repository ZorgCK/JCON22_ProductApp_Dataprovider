package com.company.productappdataprovider.controller;


import java.util.List;
import java.util.Optional;
import com.company.productappdataprovider.domain.Product;
import com.company.productappdataprovider.storage.DB;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;


@Controller("/products")
public class ProductController
{

//	@Get("/list")
//	public List<Product> getProduct()
//	{
//		return DB.root.getProducts();
//	}
	
	@Get("/list")
	public HttpResponse<List<Product>> getHttpList()
	{
		return HttpResponse.ok(DB.root.getProducts());
	}
	
	@Post("/update")
	public HttpResponse<Product> update(Product product)
	{
		Optional<Product> productOptional =
				DB.root.getProducts().stream().filter(p -> p.getProductUuid().equals(product.getProductUuid())).findFirst();
		
		if(productOptional.isPresent())
		{
			Product newProduct = productOptional.get();
			newProduct.setCategory(product.getCategory());
			newProduct.setDescription(product.getDescription());
			newProduct.setImageBytes(product.getImageBytes());
			newProduct.setImageName(product.getImageName());
			newProduct.setProductName(product.getProductName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setUnitsInStock(product.getUnitsInStock());
			newProduct.setUnitWeight(product.getUnitWeight());
			
			DB.storageManager.store(newProduct);
			
			return HttpResponse.ok(newProduct);
		}
		
		return HttpResponse.notFound();
	}
	
	@Post("/insert")
	public HttpResponse<Product> insert(Product product)
	{
		// Since UUID is randomized need to check if the same combination is already present
		// which is most likely the same product
		Optional<Product> productOptional =
				DB.root.getProducts().stream()
				.filter(p -> p.getProductName().equals(product.getProductName()))
				.filter(p -> p.getCategory().equals(product.getCategory()))
				.filter(p -> p.getUnitPrice().equals(product.getUnitPrice()))
				.filter(p -> p.getUnitWeight().equals(product.getUnitWeight()))
				.filter(p -> p.getUnitsInStock() == product.getUnitsInStock())
				.findFirst();
		
		if(!productOptional.isPresent())
		{
			Product newProduct = productOptional.get();
			newProduct.setCategory(product.getCategory());
			newProduct.setDescription(product.getDescription());
			newProduct.setImageBytes(product.getImageBytes());
			newProduct.setImageName(product.getImageName());
			newProduct.setProductName(product.getProductName());
			newProduct.setUnitPrice(product.getUnitPrice());
			newProduct.setUnitsInStock(product.getUnitsInStock());
			newProduct.setUnitWeight(product.getUnitWeight());
			
			//finally sets the given UUID
			newProduct.newUuid();
			
			DB.storageManager.store(newProduct);
			
			return HttpResponse.ok(newProduct);
		}
		
		return HttpResponse.notAllowed();
	}
	
	@Post("/delete")
	public HttpResponse<Product> delete(Product product)
	{
		Optional<Product> productOptional =
				DB.root.getProducts().stream().filter(p -> p.getProductUuid().equals(product.getProductUuid())).findFirst();
		
		if(productOptional.isPresent())
		{
			Product deleteProduct = productOptional.get();
			
			DB.root.getProducts().remove(deleteProduct);
			DB.storageManager.store(DB.root.getProducts());
			
			HttpResponse.ok("Product has been successfully deleted");
		}
		
		return HttpResponse.notFound();
	}
	
}
