package com.onlineshop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlineshop.domain.Product;

@Repository
public interface EditorProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE p.isInEditor=true")
	List<Product> findAllEditorProducts();
	
	@Transactional 
	@Modifying
	@Query("UPDATE Product p SET p.isInEditor=true WHERE p.id=:id")
	void addProductToEditor(@Param("id") Long productId);
	
	@Transactional 
	@Modifying
	@Query("UPDATE Product p SET p.isInEditor=false WHERE p.id=:id")
	void removeProductFromEditor(@Param("id") Long productId);
	
	@Transactional 
	@Modifying
	@Query("UPDATE Product p SET p.isInEditor=false WHERE p.isInEditor=true")
	void flushEditor();

}
