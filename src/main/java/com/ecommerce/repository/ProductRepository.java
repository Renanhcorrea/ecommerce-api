package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByGroupType(int groupType);
    List<Product> findByNameIgnoreCaseContaining(String name);
    List<Product> findByUnitIgnoreCaseContaining(String unit);
    List<Product> findByTypeIgnoreCaseContaining(String type);
    @Query("SELECT MAX(p.id) FROM Product p WHERE p.groupType = :groupType")
    long findMaxIdByGroupType(Integer groupType);
}
