package upt.cafetaria.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upt.cafetaria.backend.exceptions.ServiceException;
import upt.cafetaria.backend.model.domain.Product;
import upt.cafetaria.backend.model.web.ProductDto;
import upt.cafetaria.backend.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() { return productRepository.findAll();}

    public List<Product> getProductsById(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    public Product getProduct(long id) {
        return productRepository.findById(id).orElseThrow(() -> new ServiceException("GET", "product.not.found"));
    }

    public Product addProduct(ProductDto dto) {
        Product product = new Product();

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDto dto) {
        Product product = getProduct(id);

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.delete(getProduct(id));
    }
}