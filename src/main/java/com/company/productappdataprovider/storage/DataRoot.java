
package com.company.productappdataprovider.storage;

import java.util.ArrayList;
import java.util.List;

import com.company.productappdataprovider.domain.Category;
import com.company.productappdataprovider.domain.Product;


/**
 * MicroStream data root. Create your data model from here.
 * 
 * @see <a href="https://docs.microstream.one/manual/">Reference Manual</a>
 */
public class DataRoot
{
	private List<Product>			products	= new ArrayList<Product>();
	private final List<Category>	categories	= new ArrayList<Category>();
	private boolean					firstStart	= true;
	
	public List<Product> getProducts()
	{
		return products;
	}
	
	public void setProducts(final List<Product> products)
	{
		this.products = products;
	}
	
	public List<Category> getCategories()
	{
		return categories;
	}
	
	public boolean isFirstStart()
	{
		return firstStart;
	}
	
	public void setFirstStart(final boolean firstStart)
	{
		this.firstStart = firstStart;
	}
}
