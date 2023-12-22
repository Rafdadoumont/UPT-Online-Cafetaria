package upt.cafetaria.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.BDDMockito;
import upt.cafetaria.backend.BackendApplication;
import upt.cafetaria.backend.domain.SoupBuilder;
import upt.cafetaria.backend.model.domain.Soup;
import upt.cafetaria.backend.model.web.DessertDto;
import upt.cafetaria.backend.model.web.SoupDto;
import upt.cafetaria.backend.service.AuthenticationService;
import upt.cafetaria.backend.service.SoupService;
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

/**
 * Test cases for the SoupController class that verify the endpoints related to soups in the cafeteria.
 * These tests use JUnit 5 and Mockito to ensure the proper functioning of retrieving all soups, adding a soup,
 * updating a soup, and deleting a soup.
 * The tests validate the behavior of the SoupController's functionalities.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@AutoConfigureMockMvc

public class SoupControllerTest{

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private SoupService service;

    @Autowired
    private MockMvc SoupController;
    private Soup newSoup1, newSoup2;
    @Autowired
    private AuthenticationService authService;

    /**
     * Set up method executed before each test case to initialize test data.
     */
    @BeforeEach
    public void setUp(){
        newSoup1 = SoupBuilder.newSoup1().build();
        newSoup2 = SoupBuilder.newSoup2().build();
    }

    /**
     * Tests if the endpoint to retrieve all soups returns the expected list of soups.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void allSoups_IfReturn_allSoup() throws Exception{
        //Given
        List<Soup> newSoup =  Arrays.asList(newSoup1, newSoup2);

        // Mocking
        given(service.getSoups()).willReturn(newSoup);

        //When
        SoupController.perform(get("/api/soup/all")
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", Is.is(newSoup1.getName())))
                .andExpect(jsonPath("$[0].price", Is.is(newSoup1.getPrice())))
                .andExpect(jsonPath("$[0].description", Is.is(newSoup1.getDescription())));
    }

    /**
     * Tests the behavior of adding a soup through the respective endpoint.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void addSoup_IfAddSoup() throws Exception{
        //Mocking
        given(service.addSoup(BDDMockito.any(SoupDto.class))).willReturn(newSoup2);
        //When
        SoupController.perform(post("/api/soup/add")
                        .header("Authorization", "Bearer " + authService.getAccessTokenForTesting())
                        .content(mapper.writeValueAsString(newSoup2))
                        .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Is.is(newSoup2.getName())))
                .andExpect(jsonPath("$.price", Is.is(newSoup2.getPrice())))
                .andExpect(jsonPath("$.description", Is.is(newSoup2.getDescription())));
    }

    @Test
    public void updateSoup_IfReturnUpdatedSoup() throws Exception{

    }

    @Test
    public void deleteSoup_IfDeletedSoup() throws Exception{

    }

}