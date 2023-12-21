package upt.cafetaria.backend.domain.repository;//package upt.cafetaria.backend.domain.repository;
//
//import org.junit.Test;
//import upt.cafetaria.backend.controller.DrinkController;
//import upt.cafetaria.backend.model.web.DrinkDto;
//import upt.cafetaria.backend.service.DrinkService;
//
//public class DrinkControllerTest {
//
//    @Test
//    public void updateDrinkTest_ThatReturnUpdatedDrinks(){
//
//        //Given
//        DrinkService drinkService = new DrinkService();
//        DrinkDto newDrink;
//        Long id;
//        //When
//
//        //Then
//
//    };
//
//    @Test
//    public void deleteDrink_TestThatReturnDeletedDrink(){
//        //Given
//        Long id;
//
//        //When
//
//        //Then
//
//    };
//}

//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import upt.cafetaria.backend.controller.DrinkController;
//import upt.cafetaria.backend.model.domain.Drink;
//import upt.cafetaria.backend.model.enums.SugarLevelEnum;
//import upt.cafetaria.backend.model.web.DrinkDto;
//import upt.cafetaria.backend.service.DrinkService;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//@Nested
//class DrinkControllerTest {
//
//    @Test
//    public void updateDrinkTest_ThatReturnUpdatedDrinks() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        // Given
//        DrinkService drinkService = new DrinkService();
//        DrinkController drinkController = new DrinkController(drinkService);
//
//        // Add a drink to update
//        DrinkDto initialDrinkDto = new DrinkDto();
//        initialDrinkDto.setName("Initial Drink");
//        Drink initialDrink = drinkController.addDrink(initialDrinkDto);
//
//        DrinkDto updatedDrinkDto = new DrinkDto();
//        updatedDrinkDto.setName("Updated Drink");
//
//        // When
//        Method addDrinkMethod = DrinkController.class.getDeclaredMethod("addDrink", DrinkDto.class);
//        addDrinkMethod.setAccessible(true); // Allow access to the private method
//        ResponseEntity<Drink> responseEntity = (ResponseEntity<Drink>) addDrinkMethod.invoke(drinkController, updatedDrinkDto);
//
//        // Then
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(updatedDrinkDto.getName(), responseEntity.getBody().getName());
//    }
//
//    // ... (similar modification for the deleteDrink test)
//}
//
//    @Test
//    public void deleteDrink_TestThatReturnDeletedDrink() {
//        // Given
//        DrinkService drinkService = new DrinkService();
//        DrinkController drinkController = new DrinkController(drinkService);
//
//        // Add a drink to delete
//        DrinkDto drinkDto = new DrinkDto();
//        drinkDto.setName("Drink to Delete");
//        Drink drinkToDelete = drinkController.addDrink(drinkDto);
//
//        // When
//        ResponseEntity<upt.cafetaria.backend.controller.Drink> responseEntity = drinkController.deleteDrink(drinkToDelete.getId());
//
//        // Then
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(drinkToDelete.getName(), responseEntity.getBody().getName());
//
//        // Verify that the drink has been deleted
//        assertNull(drinkService.getDrink(drinkToDelete.getId()));
//    }
//}
