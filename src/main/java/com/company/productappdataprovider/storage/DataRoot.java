
package com.company.productappdataprovider.storage;

import java.util.ArrayList;
import java.util.List;

import com.company.productappdataprovider.domain.Product;


/**
 * MicroStream data root. Create your data model from here.
 * 
 * @see <a href="https://docs.microstream.one/manual/">Reference Manual</a>
 */
public class DataRoot
{
private final List<Product> products = new ArrayList<Product>();
	
	public List<Product> getProducts()
	{
		return products;
	}
}
