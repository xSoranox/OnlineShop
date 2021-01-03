package com.onlineshop.repository;

import com.onlineshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT IF(p.Name=:name, 'true', 'false') FROM Products p")
    String existsByName(@Param("name") String name);

    @Query("SELECT p FROM Products p WHERE p.Name=:name")
    List<Product> findProductsByName(@Param("name") String name);

    @Query("SELECT p FROM Products p WHERE p.Category=:category")
    List<Product> getProductsByCategory(@Param("category") String category);

    @Query("SELECT p FROM Products p WHERE p.Subcategory=:subcategory")
    List<Product> getProductsBySubcategory(@Param("subcategory") String subcategory);

    @Query("SELECT p FROM Products p WHERE p.EndPrice=:endPrice")
    List<Product> getProductsByEndPrice(@Param("endPrice") BigDecimal endPrice);

    @Query("SELECT p FROM Products p WHERE p.Discount=:discount ")
    List<Product> getProductsByDiscount(@Param("discount") BigDecimal discount);

}
