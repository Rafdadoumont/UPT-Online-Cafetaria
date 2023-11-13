package upt.cafetaria.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upt.cafetaria.backend.exceptions.ServiceException;
import upt.cafetaria.backend.model.domain.Product;
import upt.cafetaria.backend.model.web.ProductDto;
import upt.cafetaria.backend.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/add")
    Product addProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @PutMapping("/update/{id}")
    Product updateProduct(@Valid @RequestBody ProductDto newProduct, @PathVariable Long id) {
        Product updatedProduct = productService.getProduct(id);
        productService.updateProduct(id, newProduct);
        return updatedProduct;
    }

    @DeleteMapping("/delete/{id}")
    public Product deleteProduct(@PathVariable Long id) {
        Product deletedProduct = productService.getProduct(id);
        productService.deleteProduct(id);
        return deletedProduct;
    }
}
