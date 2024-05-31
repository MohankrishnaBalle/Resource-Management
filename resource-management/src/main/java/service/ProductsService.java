package service;

import entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductsRepository;

import java.util.List;

@Service
public class ProductsService {


    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products getProductById(Long productId) {
        return productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

    }

    /**
     * Saves a new product or updates an existing product.
     *
     * @param product Product to be saved or updated
     * @return Saved or updated product
     */
    public Products saveProduct(Products product) {
        // Validate the product data (e.g., check if required fields are present)
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name is required.");
        }
        return productsRepository.save(product);
    }

    // check if a product is available
    public boolean isProductAvailable(String requestedProduct) {
        List<Products> allProducts = getAllProducts();
        for (Products product : allProducts) {
            if (product.isProductAvailable(requestedProduct)) {
                return true;
            }
        }
        return false;
    }

    public void deleteProduct(Long productId) {
        Products productToDelete = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productsRepository.delete(productToDelete);

    }
}




