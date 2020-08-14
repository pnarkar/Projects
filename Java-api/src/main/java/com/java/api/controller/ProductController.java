package com.java.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.java.api.entity.Product;
import com.java.api.service.ProductService;

@RestController
public class ProductController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Something went wrong!!!";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
	@Autowired
	private ProductService proService;
	
	@RequestMapping("/")
    public String myhome(){
        return "index";
    }

	@PostMapping("/addProduct")
	public List<Product> addProducts(@RequestBody List<Product> pro) {

		return proService.saveProduct(pro);

	}

	@RequestMapping(value = "/product")
	public ModelAndView findAllProducts() {
		ModelAndView mav = new ModelAndView();
		List<Product> pro = proService.getProducts();

		mav.addObject("pro", pro);
		mav.setViewName("products.html");

		return mav;
	}

	@GetMapping("/productName/{name}")
	public Product findProByName(@PathVariable String name) {

		return proService.getProByName(name);
	}

	@GetMapping("/product/{id}")
	public Product findProById(@PathVariable int id) {

		return proService.getProById(id);
	}

	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product pro) {

		return proService.updatePro(pro);

	}

	@DeleteMapping("/deletePro/{id}")
	public String removeProduct(@PathVariable int id) {

		return proService.removePro(id);
	}

}
