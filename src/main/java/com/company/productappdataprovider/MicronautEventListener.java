package com.company.productappdataprovider;

import java.util.ArrayList;
import java.util.List;

import com.company.productappdataprovider.domain.Category;
import com.company.productappdataprovider.domain.Product;
import com.company.productappdataprovider.storage.DB;
import com.company.productappdataprovider.utils.MockupUtils;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;


@Singleton
public class MicronautEventListener
{
	@EventListener
	public void onStartup(StartupEvent event)
	{
		if(DB.root.isFirstStart())
		{
			List<Product> allCreatedProducts = MockupUtils.loadMockupData();
			
			DB.root.getProducts().addAll(allCreatedProducts);
			DB.storageManager.store(DB.root.getProducts());
			
			List<Category> allCreatedCategories = new ArrayList<Category>();
			allCreatedProducts.stream().forEach(c ->
			{
				allCreatedCategories.add(c.getCategory());
			});
			
			DB.root.getCategories().addAll(allCreatedCategories);
			DB.storageManager.store(DB.root.getCategories());
			
			DB.root.setFirstStart(false);
			DB.storageManager.store(DB.root);
		}
	}
}
