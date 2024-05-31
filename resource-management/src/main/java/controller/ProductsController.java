package controller;
import entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public List<Products> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Products getProductById(@PathVariable Long productId) {
        return productsService.getProductById(productId);
    }

    @PostMapping
    public Products saveProduct(@RequestBody Products product) {
        // Validate the product data (e.g., check if required fields are present)
        return productsService.saveProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productsService.deleteProduct(productId);
    }

    @PutMapping("/{productId}")
    public Products updateProduct(@PathVariable Long productId, @RequestBody Products updatedProduct) {
        if (updatedProduct.getProductName() == null || updatedProduct.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name is required.");
        }
        Products existingProduct = productsService.getProductById(productId);
        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setProductPrice(updatedProduct.getProductPrice());
        return productsService.saveProduct(existingProduct);
    }

    @GetMapping("/check-availability")
    public String checkProductAvailability(@RequestParam String productName) {
        boolean isAvailable = productsService.isProductAvailable(productName);
        return isAvailable ? "Product is available" : "Product is not available";
    }
}
