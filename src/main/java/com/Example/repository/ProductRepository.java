package com.Example.repository;

import com.Example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = 'Laptop' " +
            "AND p.stockQuantity < 10 AND p.supplier.city = :city")
    List<Product> findLowStockLaptopsBySupplierCity(
            @Param("city") String city);
}