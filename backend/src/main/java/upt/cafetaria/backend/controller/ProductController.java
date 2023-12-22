package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upt.cafetaria.backend.model.domain.Product;
import upt.cafetaria.backend.model.web.ProductDto;
import upt.cafetaria.backend.service.ProductService;
import java.util.List;

/**
 * Handles CRUD(Create, Read, Update, Delete) operations for products available in the cafeteria.
 * Provides endpoints to retrieve all products, update a product,
 * and delete a product with 'ADMIN' role authorization.
 *
 * Endpoints:
 * - /all: GET request to retrieve all products.
 * - /update/{id}: PUT request to update a product by ID.
 * - /delete/{id}: DELETE request to delete a product by ID (requires 'ADMIN' role).
 * @author Rúben Santos
 */
@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    List<Product> allProducts() {
        return productService.getProducts();
    }

    @GetMapping("/active/all")
    List<Product> allActiveProducts() {
        return productService.getActiveProducts();
    }

    @PutMapping("/activate/{id}")
    Product activateProduct(@PathVariable Long id) {
        return productService.activateProduct(id);
    }

    @PutMapping("/deactivate/{id}")
    Product deactivateProduct(@PathVariable Long id) {
        return productService.deactivateProduct(id);
    }

    @PutMapping("/update/{id}")
    Product updateProduct(@Valid @RequestBody ProductDto newProduct, @PathVariable Long id) {
        Product updatedProduct = productService.getProduct(id);
        productService.updateProduct(id, newProduct);
        return updatedProduct;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product deleteProduct(@PathVariable Long id) {
        Product deletedProduct = productService.getProduct(id);
        productService.deleteProduct(id);
        return deletedProduct;
    }
}
