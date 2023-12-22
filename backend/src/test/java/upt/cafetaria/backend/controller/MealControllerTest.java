package upt.cafetaria.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.BDDMockito;
import upt.cafetaria.backend.BackendApplication;
import upt.cafetaria.backend.domain.MealBuilder;
import upt.cafetaria.backend.model.domain.Meal;
import upt.cafetaria.backend.model.web.MealDto;
import upt.cafetaria.backend.model.web.SoupDto;
import upt.cafetaria.backend.service.AuthenticationService;
import upt.cafetaria.backend.service.MealService;
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

/**
 * Test cases for the MealController class that verify the endpoints related to meals in the cafeteria.
 * These tests use JUnit 5 and Mockito to ensure the proper functioning of retrieving, adding, updating, and deleting meals.
 * The tests validate the behavior of the MealController's functionalities.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@AutoConfigureMockMvc

public class MealControllerTest{
    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private MealService service;

    @Autowired
    private MockMvc MealController;

    @Autowired
    private AuthenticationService authService;
    private Meal newMeal1, newMeal2;

    /**
     * Set up method executed before each test case to initialize test data.
     */
    @BeforeEach
    public void setUp(){
        newMeal1 = MealBuilder.newMeal1().build();
        newMeal2 = MealBuilder.newMeal2().build();
    }


    /**
     * Tests if the endpoint to retrieve all meals returns the expected list of meals.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void allMeal_IfReturn_allMeal() throws Exception{
        //Given
        List<Meal> newMeal =  Arrays.asList(newMeal1, newMeal2);

        // Mocking
        given(service.getMeals()).willReturn(newMeal);

        //When
        MealController.perform(get("/api/meal/all")
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", Is.is(newMeal1.getName())))
                .andExpect(jsonPath("$[0].price", Is.is(newMeal1.getPrice())))
                .andExpect(jsonPath("$[0].description", Is.is(newMeal1.getDescription())));
    }


    /**
     * Tests the behavior of adding a meal through the respective endpoint.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void addMeal_IfAddMeal() throws Exception{
        //Mocking
        given(service.addMeal(BDDMockito.any(MealDto.class))).willReturn(newMeal2);
        //When
        MealController.perform(post("/api/meal/add")
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .content(mapper.writeValueAsString(newMeal2))
                        .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Is.is(newMeal2.getName())))
                .andExpect(jsonPath("$.price", Is.is(newMeal2.getPrice())))
                .andExpect(jsonPath("$.description", Is.is(newMeal2.getDescription())));
    }

    @Test
    public void updateMeal_IfReturnUpdatedMeal() throws Exception{

    }

    @Test
    public void deleteMeal_IfDeletedMeal() throws Exception{

    }
}
