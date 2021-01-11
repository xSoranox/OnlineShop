package com.onlineshop.repository;

import com.onlineshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*
    @Query(value = "SELECT IF(p.Name=:name, 'true', 'false') FROM Products p")
    String existsByName(@Param("name") String name);
*/
    @Query(value = "SELECT p FROM Product p WHERE p.name=:name")
    List<Product> findProductsByName(@Param("name") String name);

    @Query(value = "SELECT p FROM Product p WHERE p.category=:category")
    List<Product> getProductsByCategory(@Param("category") String category);

    @Query(value = "SELECT p FROM Product p WHERE p.subcategory=:subcategory")
    List<Product> getProductsBySubcategory(@Param("subcategory") String subcategory);

    @Query(value = "SELECT p FROM Product p WHERE p.endPrice=:endPrice")
    List<Product> getProductsByEndPrice(@Param("endPrice") BigDecimal endPrice);

    @Query(value = "SELECT p FROM Product p WHERE p.discount=:discount")
    List<Product> getProductsByDiscount(@Param("discount") BigDecimal discount);
    
	@Transactional
	@Modifying
	@Query("DELETE FROM Product p WHERE p.id = :id")
	void deleteProductById(@Param("id") Long id);

}
