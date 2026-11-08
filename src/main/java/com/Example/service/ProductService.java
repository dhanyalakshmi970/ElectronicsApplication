package com.Example.service;

import com.Example.entity.Product;
import com.Example.entity.Supplier;
import com.Example.repository.ProductRepository;
import com.Example.repository.SupplierRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository,
                          SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    // CREATE
    public Product addProduct(Product product, Long supplierId) {

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() ->
                        new RuntimeException("Supplier not found"));

        product.setSupplier(supplier);

        return productRepository.save(product);
    }

    // READ
    public Product getProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
    }

    // UPDATE
    public Product updateProduct(Long id, Product newProduct) {

        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        oldProduct.setProductName(newProduct.getProductName());
        oldProduct.setCategory(newProduct.getCategory());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setStockQuantity(newProduct.getStockQuantity());

        if (newProduct.getSupplier() != null) {
            Long supplierId =
                    newProduct.getSupplier().getSupplierId();

            Supplier supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() ->
                            new RuntimeException("Supplier not found"));

            oldProduct.setSupplier(supplier);
        }

        return productRepository.save(oldProduct);
    }

    // DELETE
    public void deleteProduct(Long id) {

        Product product = getProduct(id);

        productRepository.delete(product);
    }

    // CONDITIONAL SELECT
    public List<Product> getLowStockLaptops(String city) {

        return productRepository
                .findLowStockLaptopsBySupplierCity(city);
    }
}