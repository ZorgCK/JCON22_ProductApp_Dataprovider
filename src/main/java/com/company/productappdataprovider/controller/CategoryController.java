package com.company.productappdataprovider.controller;

import java.util.List;

import com.company.productappdataprovider.domain.Category;
import com.company.productappdataprovider.storage.DB;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/categories")
public class CategoryController
{
		@Get("/list")
		public List<Category> getCategories()
		{
			return DB.root.getCategories();
		}
}
