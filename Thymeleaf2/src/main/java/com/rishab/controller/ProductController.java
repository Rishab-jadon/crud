package com.rishab.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rishab.entity.Product;
import com.rishab.repository.ProductRepository;

@Controller
public class ProductController {
	
	@Autowired
	ProductRepository repo;
	
	@GetMapping("/")
	public String loadIndexPage(Model model) {
		Product product=new Product();

		model.addAttribute("product",product);
		return "index";
	}

	@PostMapping("/save")
	public String handleSave(@Validated Product product,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "index";
		}
		
		Product p = repo.save(product);
		if(p.getId()!=null) {
			model.addAttribute("msg","product saved");
		}
		return "index";
	}
	
	@GetMapping("/products")
	public String handleProducts(Model model) {
		List<Product> all = repo.findAll();
		model.addAttribute("products",all);
		return "products";
		
	}
	
	@GetMapping("/edit")
	public String handleEdit(@RequestParam("id")Integer id,Model model) {
		Optional<Product> p = repo.findById(id);
		if(p.isPresent()) {
			Product product = p.get();
			repo.save(product);
			model.addAttribute("product",product);
		}
		return "index";
		
	}
	
	
	@GetMapping("/delete")
	public String handleDelete(@RequestParam("id")Integer id,Model model) {
		Optional<Product> p = repo.findById(id);
		if(p.isPresent()) {
			repo.deleteById(id);
			model.addAttribute("msg","deleted success");
			model.addAttribute("products",repo.findAll());
		}
		return "products";
		
	}
}
