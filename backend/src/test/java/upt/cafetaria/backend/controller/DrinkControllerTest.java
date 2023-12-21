package upt.cafetaria.backend.controller;

import org.springframework.security.core.token.TokenService;
import upt.cafetaria.backend.BackendApplication;
import upt.cafetaria.backend.domain.DrinkBuilder;
import upt.cafetaria.backend.domain.DrinkBuilder;
import upt.cafetaria.backend.model.domain.Drink;
import upt.cafetaria.backend.service.AuthenticationService;
import upt.cafetaria.backend.service.DrinkService;

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
import upt.cafetaria.backend.service.DrinkService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@AutoConfigureMockMvc
public class DrinkControllerTest {

    @MockBean
    private DrinkService service;

    @Autowired
    private MockMvc DrinkController;

    @Autowired
    private AuthenticationService authService;
    private Drink newDrink1, newDrink2;

    @BeforeEach
    public void setUp(){
        newDrink1 = DrinkBuilder.newDrink1().build();
        newDrink2 = DrinkBuilder.newDrink2().build();
    };

    @Test
    public void allDrinks_IfReturn_allDrink() throws Exception {
        //Given
        List<Drink> newDrink =  Arrays.asList(newDrink1, newDrink2);

        // Mocking
        given(service.getDrinks()).willReturn(newDrink);

        //When
        DrinkController.perform(get("/api/drink/all")
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", Is.is(newDrink1.getName())))
                .andExpect(jsonPath("$[0].price", Is.is(newDrink1.getPrice())))
                .andExpect(jsonPath("$[0].description", Is.is(newDrink1.getDescription())));

    };
    @Test
    public void addDrink_IfAddDrink() throws Exception{

    };

    @Test
    public void updateDrink_IfReturnUpdatedDrink() throws Exception{

    };
    @Test
    public void deleteDrink_IfDeletedDrink() throws Exception{

    };

    @Test
    public void allSugarLevels_IfReturnSugarLevel() throws Exception{

    };
}
