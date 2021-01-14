package com.onlineshop.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlineshop.domain.CartProduct;
import com.onlineshop.domain.ShoppingCart;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
	
	@Query("SELECT cp FROM CartProduct cp "
			+ "INNER JOIN ShoppingCart sc ON sc.id = cp.shoppingCart "
			+ "WHERE sc.id=:id")
	List<CartProduct> findProductsByCartId(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CartProduct cp WHERE cp.shoppingCart = :shoppingcart")
	void deleteAllCartProductsByCart(@Param("shoppingcart") ShoppingCart shoppingcart);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CartProduct cp WHERE cp = :cartProduct")
	void deleteProductFromCart(@Param("cartProduct") CartProduct cartProduct);
	
	@Query("SELECT cp FROM CartProduct cp "
			+ "INNER JOIN ShoppingCart sc ON sc.id = cp.shoppingCart "
			+ "WHERE cp.name=:name")
	List<CartProduct> findProductsByName(@Param("name") String name);

	@Query("SELECT cp FROM CartProduct cp "
			+ "INNER JOIN ShoppingCart sc ON sc.id = cp.shoppingCart "
			+ "ORDER BY cp.priceBeforeDiscount DESC")
	List<CartProduct> getProductsByDescendingPriceBeforeDiscount();

	@Query("SELECT cp FROM CartProduct cp "
			+ "INNER JOIN ShoppingCart sc ON sc.id = cp.shoppingCart "
			+ "ORDER BY cp.priceBeforeDiscount ASC")
	List<CartProduct> getProductsByAscendingPriceBeforeDiscount();

	@Query("SELECT cp FROM CartProduct cp "
			+ "INNER JOIN ShoppingCart sc ON sc.id = cp.shoppingCart "
			+ "ORDER BY cp.endPrice DESC")
	List<CartProduct> getProductsByDescendingEndPrice();

	@Query("SELECT cp FROM CartProduct cp "
			+ "INNER JOIN ShoppingCart sc ON sc.id = cp.shoppingCart "
			+ "ORDER BY cp.endPrice ASC")
	List<CartProduct> getProductsByAscendingEndPrice();

	@Query("SELECT cp FROM CartProduct cp "
			+ "INNER JOIN ShoppingCart sc ON sc.id = cp.shoppingCart "
			+ "ORDER BY cp.discount DESC")
	List<CartProduct> getProductsByDescendingDiscount();

	@Query("SELECT cp FROM CartProduct cp "
			+ "INNER JOIN ShoppingCart sc ON sc.id = cp.shoppingCart "
			+ "ORDER BY cp.discount ASC")
	List<CartProduct> getProductsByAscendingDiscount();
	
    @Query("SELECT cp FROM CartProduct cp WHERE cp.originalProductId = :originalProductId")
    Optional<CartProduct> returnsCartProductIfExists(@Param("originalProductId") Long originalProductId);

}
