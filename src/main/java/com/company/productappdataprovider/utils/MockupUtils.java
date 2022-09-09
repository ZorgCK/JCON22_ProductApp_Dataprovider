package com.company.productappdataprovider.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.company.productappdataprovider.domain.Category;
import com.company.productappdataprovider.domain.Product;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;

public class MockupUtils
{
	public static List<Product> loadMockupData()
	{
		List<Product> products = new ArrayList<Product>();
		List<Category> categories = new ArrayList<Category>();
		
		ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
		Optional<URL> categoriesURL = loader.getResource("mockup/Categories.json");
		Optional<URL> productsURL = loader.getResource("mockup/Products.json");
		
		JSONParser productParser = new JSONParser();
		JSONParser categoryParser = new JSONParser();
		
		try
		{
			FileReader categoryReader = new FileReader(new File(categoriesURL.get().getFile()));
			FileReader productReader = new FileReader(new File(productsURL.get().getFile()));
			// Read JSON file
			Object categoryJSON = categoryParser.parse(categoryReader);
			JSONArray categoryList = (JSONArray)categoryJSON;
			// Iterate over employee array
			categoryList.forEach(emp ->
			{
				Category a = parseCategoryObject((JSONObject)emp);
				categories.add(a);
			});
			
			// Read JSON file
			Object productJSON = productParser.parse(productReader);
			JSONArray productList = (JSONArray)productJSON;
			// Iterate over employee array
			productList.forEach(emp ->
			{
				Product product = parseProductObject((JSONObject)emp, categories);
				products.add(product);
			});
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		
		return products;
	}

	private static Product parseProductObject(JSONObject product, List<Category> categories)
	{
		Product p = new Product();
		
		p.setProductUuid((String)product.get("uuid"));
		p.setProductName((String)product.get("name"));
		p.setDescription((String)product.get("description"));
		
		p.setUnitPrice(new BigDecimal(product.get("price").toString()));
		p.setUnitWeight((Double)(product.get("weight")));
		p.setUnitsInStock(Integer.parseInt(product.get("stock").toString()));
		
		String cat = (String)product.get("category");
		p.setCategory(categories.stream().filter(c -> c.getCategoryUuid().equals(cat)).findFirst().get());
								
		return p;
	}
	
	private static Category parseCategoryObject(JSONObject category)
	{
		Category c = new Category();
		
		c.setCategoryUuid((String)category.get("uuid"));
		c.setCategoryName((String)category.get("categoryName"));
				
		return c;
	}
	
}
