package upt.cafetaria.backend.domain.model;


import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import upt.cafetaria.backend.BackendApplication;
import upt.cafetaria.backend.domain.DessertBuilder;
import upt.cafetaria.backend.model.domain.Dessert;
import upt.cafetaria.backend.service.DessertService;
import java.util.Arrays;
import java.util.List;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@AutoConfigureMockMvc
public class DessertControllerTest extends AbstractTest {

    @MockBean
    private DessertService service;

    @Autowired
    private MockMvc DessertController;
    private Dessert newDessert1, newDessert2;

    @Override
    @BeforeEach
    public void setUp(){
        newDessert1 = DessertBuilder.newDessert1().build();
        newDessert2 = DessertBuilder.newDessert2().build();
    };

    @Test
    public void updateDessert_IfReturn_updatedDessert() throws Exception{
        //Given
        List<Dessert> newDessert =  Arrays.asList(newDessert1, newDessert2);

        // Mocking
        given(service.getDesserts()).willReturn(newDessert);
        //When
        DessertController.perform(get("/api/dessert/overview")
                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", Is.is(newDessert1.getName())))
                .andExpect(jsonPath("$[0].price", Is.is(newDessert1.getPrice())))
                .andExpect(jsonPath("$[0].description", Is.is(newDessert1.getDescription())));
//                .andExpect(jsonPath("$[0].isActive", Is.is(newDessert1.getActive)));
    };
}
