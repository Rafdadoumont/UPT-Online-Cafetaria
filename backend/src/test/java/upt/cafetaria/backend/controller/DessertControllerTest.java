package upt.cafetaria.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.BDDMockito;
import upt.cafetaria.backend.BackendApplication;
import upt.cafetaria.backend.domain.DessertBuilder;
import upt.cafetaria.backend.model.domain.Dessert;
import upt.cafetaria.backend.model.web.DessertDto;
import upt.cafetaria.backend.service.AuthenticationService;
import upt.cafetaria.backend.service.DessertService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@AutoConfigureMockMvc
public class DessertControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private DessertService service;

    @Autowired
    private MockMvc DessertController;

    @Autowired
    private AuthenticationService authService;

    private Dessert newDessert1, newDessert2, newDessert4;
    private DessertDto newDessert3;

    @BeforeEach
    public void setUp(){
        newDessert1 = DessertBuilder.newDessert1().build();
        newDessert2 = DessertBuilder.newDessert2().build();
        newDessert3 = DessertBuilder.newDessert3().build2();
        newDessert4 = DessertBuilder.newDessert3().build();
    }

    @Test
    public void givenDesserts_whenGetRequestToAllDesserts_thenReturnJSONWithAllDesserts() throws Exception {
        //Given
        List<Dessert> newDessert =  Arrays.asList(newDessert1, newDessert2);

        // Mocking
        given(service.getDesserts()).willReturn(newDessert);

        //When
        DessertController.perform(get("/api/dessert/all")
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", Is.is(newDessert1.getName())))
                .andExpect(jsonPath("$[0].price", Is.is(newDessert1.getPrice())))
                .andExpect(jsonPath("$[0].description", Is.is(newDessert1.getDescription())));
    }

    @Test
    public void givenDessert_whenPOSTRequestToAddDessert_thenDessertIsAdded() throws Exception {
        //Mocking
        given(service.addDessert(BDDMockito.any(DessertDto.class))).willReturn(newDessert4);
        //When
        DessertController.perform(post("/api/dessert/add")
                    .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                    .content(mapper.writeValueAsString(newDessert4))
                    .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Is.is(newDessert4.getName())))
                .andExpect(jsonPath("$.price", Is.is(newDessert4.getPrice())))
                .andExpect(jsonPath("$.description", Is.is(newDessert4.getDescription())));
    }

    @Test
    public void updateDessert_IfReturn_updatedDessert() throws Exception{
        //Given
        //Mocking
        //When
        //Then

    }

    @Test
    public void deleteDessert_IfReturn_DessertWithDeletedPostion() throws Exception{

    }
}
