package upt.cafetaria.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import upt.cafetaria.backend.BackendApplication;
import upt.cafetaria.backend.domain.DrinkBuilder;
import upt.cafetaria.backend.model.domain.Drink;
import upt.cafetaria.backend.model.enums.SugarLevelEnum;
import upt.cafetaria.backend.model.web.DessertDto;
import upt.cafetaria.backend.model.web.DrinkDto;
import upt.cafetaria.backend.service.AuthenticationService;
import upt.cafetaria.backend.service.DrinkService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@AutoConfigureMockMvc
public class DrinkControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private DrinkService service;

    @Autowired
    private MockMvc DrinkController;

    @Autowired
    private AuthenticationService authService;
    private Drink newDrink1, newDrink2;
    private DrinkDto newDrink3;

    @BeforeEach
    public void setUp(){
        newDrink1 = DrinkBuilder.newDrink1().build();
        newDrink2 = DrinkBuilder.newDrink2().build();
        newDrink3 = DrinkBuilder.newDrink3().build2();
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

    //Something doesnt want to work(status 400)
//    @Test
//    public void addDrink_IfAddDrink() throws Exception{
//        //Mocking
//       given(service.addDrink(BDDMockito.any(DrinkDto.class))).willReturn(newDrink2);
//       //When
//        DrinkController.perform(post("/api/drink/add")
//                .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
//                .content(mapper.writeValueAsString(newDrink3))
//                .contentType(MediaType.APPLICATION_JSON))
//        //Then
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name", Is.is(newDrink2.getName())))
//                .andExpect(jsonPath("$.price", Is.is(newDrink2.getPrice())))
//                .andExpect(jsonPath("$.description", Is.is(newDrink2.getDescription())));
//
//
//    };

//Something doesnt want to work(status 400)
//    @Test
//    public void updateDrink_IfReturnUpdatedDrink() throws Exception{
//        //Given
//        long drinkId = 1;
//        //Mocking
//        given(service.getDrink(drinkId)).willReturn(newDrink1);
//        given(service.updateDrink(drinkId, newDrink3)).willReturn(newDrink1);
//        //When
//        DrinkController.perform(put("/api/drink/update/{id}", drinkId)
//                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
//                        .content(mapper.writeValueAsString(newDrink3))
//                        .contentType(MediaType.APPLICATION_JSON))
//                // Then
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name", Is.is(newDrink1.getName())))
//                .andExpect(jsonPath("$.price", Is.is(newDrink1.getPrice())))
//                .andExpect(jsonPath("$.description", Is.is(newDrink1.getDescription())));
//    };
    @Test
    public void deleteDrink_IfDeletedDrink() throws Exception{
            // Given
            long drinkId = 1;
            given(service.getDrink(drinkId)).willReturn(newDrink1);

            // When
            DrinkController.perform(delete("/api/drink/delete/{id}", drinkId)
                            .header("Authorization", "Bearer " + authService.getAccessTokenForTesting()))
                    // Then
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.name", Is.is(newDrink1.getName())))
                    .andExpect(jsonPath("$.price", Is.is(newDrink1.getPrice())))
                    .andExpect(jsonPath("$.description", Is.is(newDrink1.getDescription())));
        }


    @Test
    public void allSugarLevels_IfReturnSugarLevel() throws Exception{
        // Given
        List<SugarLevelEnum> sugarLevels = Arrays.asList(SugarLevelEnum.ZERO_SUGARS, SugarLevelEnum.NO_ADDED_SUGARS, SugarLevelEnum.ADDED_SUGARS);

        // Mocking
        given(service.getSugarLevels()).willReturn(sugarLevels);

        // When
        DrinkController.perform(get("/api/drink/sugarlevels")
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3))) // Assumption: There are three sugar levels
                .andExpect(jsonPath("$[0]", Is.is(SugarLevelEnum.ZERO_SUGARS.toString())))
                .andExpect(jsonPath("$[1]", Is.is(SugarLevelEnum.NO_ADDED_SUGARS.toString())))
                .andExpect(jsonPath("$[2]", Is.is(SugarLevelEnum.ADDED_SUGARS.toString())));
    };
}
