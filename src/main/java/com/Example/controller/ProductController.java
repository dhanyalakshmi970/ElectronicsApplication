package com.Example.controller;

import com.Example.entity.Product;
import com.Example.service.ProductService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE
    @PostMapping("/{supplierId}")
    public Product addProduct(
            @RequestBody Product product,
            @PathVariable Long supplierId) {

        return productService.addProduct(product, supplierId);
    }

    // READ
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {

        return productService.getProduct(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product deleted successfully";
    }

    // CONDITIONAL SELECT
    @GetMapping("/low-stock-laptops")
    public List<Product> getLowStockLaptops(
            @RequestParam String city) {

        return productService.getLowStockLaptops(city);
    }
}