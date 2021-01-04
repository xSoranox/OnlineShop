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

    /*
    @Query(value = "SELECT IF(p.Name=:name, 'true', 'false') FROM Products p")
    String existsByName(@Param("name") String name);
*/
    @Query(value = "SELECT p FROM Products p WHERE p.Name=:name", nativeQuery = true)
    List<Product> findProductsByName(@Param("name") String name);

    @Query(value = "SELECT p FROM Products p WHERE p.Category=:category", nativeQuery = true)
    List<Product> getProductsByCategory(@Param("category") String category);

    @Query(value = "SELECT p FROM Products p WHERE p.Subcategory=:subcategory", nativeQuery = true)
    List<Product> getProductsBySubcategory(@Param("subcategory") String subcategory);

    @Query(value = "SELECT p FROM Products p WHERE p.EndPrice=:endPrice", nativeQuery = true)
    List<Product> getProductsByEndPrice(@Param("endPrice") BigDecimal endPrice);

    @Query(value = "SELECT p FROM Products p WHERE p.Discount=:discount", nativeQuery = true)
    List<Product> getProductsByDiscount(@Param("discount") BigDecimal discount);

}
