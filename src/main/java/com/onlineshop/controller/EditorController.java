package com.onlineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlineshop.domain.Product;
import com.onlineshop.service.EditorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditorController {
	
    @Autowired
    private EditorService editorService;
    
    @RequestMapping("/adminmode/editor")
    public ModelAndView getAllEditorProducts() {
        List<Product> editorProducts = editorService.findAllProducts();
        ModelAndView modelAndView = new ModelAndView("list-editor-products");
        modelAndView.addObject("editorProducts", editorProducts);
        return modelAndView;
    }
    
    @RequestMapping("/adminmode/editor/add/{productId}")
    public String addProductToEditor(@PathVariable("productId") Long productId) {
    	editorService.addProductToEditor(productId);
		return "redirect:/adminmode/";
    }
	
    @RequestMapping("/adminmode/editor/setDiscount")
    public String setDiscountToEditorProducts(@RequestParam String discount) {
    	editorService.setDiscountToEditorProducts(discount);
        return "redirect:/adminmode/editor/";
    }
    
    @RequestMapping("/adminmode/editor/flush")
    public String deleteProductsFromEditor() {
    	editorService.deleteAllProductsFromEditor();
        return "redirect:/adminmode/editor/";
    }
    
    @RequestMapping("/adminmode/editor/delete/{productId}")
    public String deleteSingleProductFromEditor(@PathVariable("productId") Long productId) {
    	editorService.deleteProductFromEditor(productId);
        return "redirect:/adminmode/editor/";
    }
}
