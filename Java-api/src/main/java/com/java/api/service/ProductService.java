package com.java.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.api.entity.Product;
import com.java.api.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository proRepository;

	public List<Product> saveProduct(List<Product> pro) {

		return proRepository.saveAll(pro);
	}

	public Product getProById(int id) {

		return proRepository.findById(id).orElse(null);
	}

	public Product getProByName(String name) {

		return proRepository.findByName(name);
	}

	public List<Product> getProducts() {

		return proRepository.findAll();
	}

	public String removePro(int id) {
		
		proRepository.deleteById(id);
		
		return "Product id " +id +" deleted";
		
	}
	
	public Product updatePro(Product pro) {
		
		Product proOld = proRepository.findById(pro.getId()).orElse(null);
		
		if(proOld!= null) {
			
			proOld.setName(pro.getName());
			proOld.setPrice(pro.getPrice());
			proOld.setQnty(pro.getQnty());
		}
		
		return proRepository.save(proOld);
	}
}
