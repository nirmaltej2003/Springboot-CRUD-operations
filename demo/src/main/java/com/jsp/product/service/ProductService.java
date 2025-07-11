package com.jsp.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jsp.product.entity.Product;
import com.jsp.product.repository.ProductRepository;



@Component
@Service
public class ProductService {

	@Autowired
	ProductRepository repository;
	
	public void add ( Product product, ModelMap map)
	{
		repository.save(product);
		map.put("pass", "Product added success");
	}
	public List<Product> findRecords()
	{
		return repository.findAll();
	}
    public void delete(int id, ModelMap map) {
	repository.deleteById(id);
	map.put("pass", "Product deleted success");
	}
	public void edit(int id, ModelMap map) {
	  Product product =repository.findById(id).get();
	  map.put("product", product);
	}
	public void update(Product product,ModelMap map) {
	repository.save(product);
	map.put("pass", "Updated success");
	}
	
}
