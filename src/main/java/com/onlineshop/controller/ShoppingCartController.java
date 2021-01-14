package com.onlineshop.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlineshop.domain.CartProduct;
import com.onlineshop.service.ShoppingCartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@RequestMapping("/shoppingCart/{cartId}")
	public ModelAndView getAllProducts(@PathVariable("cartId") Long cartId) {
		List<CartProduct> cartProducts = shoppingCartService.findAllCartProducts(cartId);
		BigDecimal totalSum = shoppingCartService.calculateTotalSum(cartProducts);
		ModelAndView modelAndView = new ModelAndView("list-cart-products");
		modelAndView.addObject("cartproducts", cartProducts);
		modelAndView.addObject("totalSum", totalSum);
		return modelAndView;
	}
	
	@RequestMapping("/shoppingCart/addProduct/{cartId}/{productId}")
	public String addProductToCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
		shoppingCartService.addProductToCart(cartId, productId);
		return "redirect:/";
	}

	@RequestMapping("/shoppingCart/flush/{cartId}")
	public String deleteAllProductsFromShoppingCart(@PathVariable("cartId") Long cartId) {
		shoppingCartService.deleteAllCartProducts(cartId);
		return "redirect:/shoppingCart/" + cartId;
	}
	
	@RequestMapping("/shoppingCart/delete/{cartId}/{productId}")
	public String deleteProductFromShoppingCart(@PathVariable("cartId") Long cartId, 
			@PathVariable("productId") Long productId) {
		shoppingCartService.reduceCartProduct(cartId, productId, true);
		return "redirect:/shoppingCart/" + cartId;
	}
	
	@RequestMapping("/shoppingCart/reduceProduct/{cartId}/{productId}")
	public String reduceCartProductQuantity(@PathVariable("cartId") Long cartId, 
			@PathVariable("productId") Long productId) {
		shoppingCartService.reduceCartProduct(cartId, productId, false);
		return "redirect:/shoppingCart/" + cartId;
	}
	
	@RequestMapping("/shoppingCart/increaseProduct/{cartId}/{productId}")
	public String increaseCartProductQuantity(@PathVariable("cartId") Long cartId, 
			@PathVariable("productId") Long productId) {
		shoppingCartService.increaseProductQuantity(cartId, productId);
		return "redirect:/shoppingCart/" + cartId;
	}
}
