package com.onlineshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlineshop.domain.Product;
import com.onlineshop.service.ProductService;

@Controller
public class AdminModeController {
	
    @Autowired
    private ProductService productService;
	
    @RequestMapping("/adminmode")
    public ModelAndView failedConnection() {
    	List<Product> products = productService.findAllProducts();
        ModelAndView modelAndView = new ModelAndView("admin-mode");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    
    @RequestMapping("/adminmode/searchByName")
    public ModelAndView findProductsByName(@RequestParam String productName) {
        List<Product> products = productService.findProductsByName(productName);
        ModelAndView modelAndView = new ModelAndView("admin-mode");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    
    @RequestMapping("/adminmode/searchById")
    public ModelAndView findProductsById(@RequestParam String productId) {
        List<Product> products = productService.findProductsById(productId);
        ModelAndView modelAndView = new ModelAndView("admin-mode");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    
    @RequestMapping("/adminmode/findbytype/{productType}")
    public ModelAndView getProductsByType(@PathVariable("productType") String productType) {
        List<Product> products = productService.findProductsByType(productType);
        ModelAndView modelAndView = new ModelAndView("admin-mode");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    
	@RequestMapping("/adminmode/delete/{productId}")
	public String deleteProductFromShoppingCart(@PathVariable("productId") Long productId) {
		productService.deleteProductById(productId);
		return "redirect:/adminmode";
	}
    
    @RequestMapping("adminmode/editProduct/{id}")
    public ModelAndView editProduct(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-product");
        Optional<Product> product = productService.findProductById(id);
        product.ifPresent(p -> modelAndView.addObject("product", p));
        return modelAndView;
    }
    
    @RequestMapping("/adminmode/editProduct/saveEditedProduct")
    public ModelAndView saveEditedBook(@ModelAttribute Product product) {
    	productService.saveNewProduct(product);
    	return new ModelAndView("redirect:/adminmode");
    }
    
    @RequestMapping("/adminmode/createProduct")
    public ModelAndView createProduct(ModelAndView modelAndView) {
    	Product product = productService.createProduct();
        return new ModelAndView("new-product", "command", product);
    }
    
    @RequestMapping(value = "/adminmode/saveProduct", method = RequestMethod.POST)
    public ModelAndView saveProduct(@ModelAttribute("SpringWeb") Product product, ModelMap model) {
    	productService.saveNewProduct(product);
    	return new ModelAndView("redirect:/adminmode");
    }
    


}
