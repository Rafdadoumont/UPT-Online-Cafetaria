package upt.cafetaria.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import upt.cafetaria.backend.BackendApplication;
import upt.cafetaria.backend.model.web.ProductDto;
import upt.cafetaria.backend.service.AuthenticationService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import upt.cafetaria.backend.domain.ProductBuilder;
import upt.cafetaria.backend.model.domain.Product;
import upt.cafetaria.backend.service.ProductService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@AutoConfigureMockMvc
public class ProductControllerTest {
    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ProductService service;

    @Autowired
    private MockMvc ProductController;

    @Autowired
    private AuthenticationService authService;

    private Product product1, product2;
    private ProductDto product3;
    @BeforeEach
    public void setUp(){
    product1 = ProductBuilder.product1().build();
    product2 = ProductBuilder.product2().build();
    product3 = ProductBuilder.product3().build2();
    };
    @Test
    public void allProucts_IfReturnAllProducts() throws Exception{
    List<Product> products = Arrays.asList(product1, product2);

    given(service.getProducts()).willReturn(products);

        ProductController.perform(get("/api/product/all")
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", Is.is(product1.getName())))
                .andExpect(jsonPath("$[0].price", Is.is(product1.getPrice())))
                .andExpect(jsonPath("$[0].description", Is.is(product1.getDescription())))
                .andExpect(jsonPath("$[0].productId", Is.is((int) product1.getProductId())))
                .andExpect(jsonPath("$[0].active", Is.is(product1.isActive())));
    };
    @Test
    public void allActiveProucts_IfReturnAllActiveProducts() throws Exception {
        // Given
        List<Product> activeProducts = Arrays.asList(product1, product2);
        given(service.getActiveProducts()).willReturn(activeProducts);

        // When/Then
        ProductController.perform(get("/api/product/active/all")
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", Is.is(product1.getName())))
                .andExpect(jsonPath("$[0].price", Is.is(product1.getPrice())))
                .andExpect(jsonPath("$[0].description", Is.is(product1.getDescription())))
                .andExpect(jsonPath("$[0].active", Is.is(product1.isActive())));
    };

    @Test
    public void activeProduct_IfReturnActiveProuctsById() throws Exception {
        // Given
        long productId = 1;
        given(service.activateProduct(productId)).willReturn(product1);

        // When/Then
        ProductController.perform(put("/api/product/activate/{id}", productId)
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Is.is(product1.getName())))
                .andExpect(jsonPath("$.price", Is.is(product1.getPrice())))
                .andExpect(jsonPath("$.description", Is.is(product1.getDescription())))
                .andExpect(jsonPath("$.active", Is.is(product1.isActive())));
    };

    @Test
    public void deactiveProduct_IfReturnDeactiveProductById() throws Exception {
        // Given
        long productId = 1;
        given(service.deactivateProduct(productId)).willReturn(product1);

        // When/Then
        ProductController.perform(put("/api/product/deactivate/{id}", productId)
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Is.is(product1.getName())))
                .andExpect(jsonPath("$.price", Is.is(product1.getPrice())))
                .andExpect(jsonPath("$.description", Is.is(product1.getDescription())))
                .andExpect(jsonPath("$.active", Is.is(product1.isActive())));
    };

    @Test
    public void updateProduct_IfReturnUpdatedProcuctById() throws Exception {
        //Given
        long productId = 1;
        //Mocking
        given(service.getProduct(productId)).willReturn(product1);
        given(service.updateProduct(productId, product3)).willReturn(product1);
        //When
        ProductController.perform(put("/api/product/update/{id}", productId)
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .content(mapper.writeValueAsString(product1))
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Is.is(product1.getName())))
                .andExpect(jsonPath("$.price", Is.is(product1.getPrice())))
                .andExpect(jsonPath("$.productId", Is.is((int) product1.getProductId())))
                .andExpect(jsonPath("$.description", Is.is(product1.getDescription())))
                .andExpect(jsonPath("$.active", Is.is(product1.isActive())));
    };

    @Test
    public void deleteProduct_IfReturnDeletedProductById() throws Exception {
        // Given
        long productId = 1;
        given(service.getProduct(productId)).willReturn(product1);

        // When
        ProductController.perform(delete("/api/product/delete/{id}", productId)
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting()))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Is.is(product1.getName())))
                .andExpect(jsonPath("$.price", Is.is(product1.getPrice())))
                .andExpect(jsonPath("$.description", Is.is(product1.getDescription())))
                .andExpect(jsonPath("$.active", Is.is(product1.isActive())));
    }

}
