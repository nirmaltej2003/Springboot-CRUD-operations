package com.jsp.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.jsp.product.entity.Product;
import com.jsp.product.service.ProductService;



@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
    
@GetMapping("/")
public String login()
{	
	return "login.html";
}
@GetMapping("/login")
public String login1() {
	return "main.html";
}
 @PostMapping("/login")
 public String loadMain(@RequestParam String username,@RequestParam String password,ModelMap map)
 {
	 
	 if(username.equals("Nirmal") && password.equals("1234") )
		{
			return "main.html";
		}
	 else if(username.equals("Priyanka") && password.equals("4321") )
	 {
		 List<Product> product=service.findRecords();
	     map.put("Products", product);
		 return "User.html";
		 
	 }
		else {
			map.put("error", "Invaild Password");
			return "Login.html";
		}
 }
@GetMapping("/add")
public String loadadd()
{
	
	return "add.html";
}
@PostMapping("/add")
public String add(@ModelAttribute Product product,ModelMap map) {
    service.add(product, map);   
      return "main.html";
}


	@GetMapping("/view")
	public String view(ModelMap map)
	{
		List<Product> product=service.findRecords();
		map.put("Products", product);
		return "view.html";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id,ModelMap map)
	{
		service.delete(id,map);
		return view(map);
	}
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id,ModelMap map)
	{
		service.edit(id,map);
		return "edit.html";
	}
	@PostMapping("/edit")
	public String update(@ModelAttribute Product product,ModelMap map)
	{
		service.update(product,map);
		return view(map);
		
	}
}
