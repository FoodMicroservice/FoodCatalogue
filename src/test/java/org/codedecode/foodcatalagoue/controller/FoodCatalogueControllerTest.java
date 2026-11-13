
package org.codedecode.foodcatalagoue.controller;

import org.codedecode.foodcatalagoue.dto.FoodCataloguePage;
import org.codedecode.foodcatalagoue.dto.FoodItemDTO;
import org.codedecode.foodcatalagoue.dto.Restaurant;
import org.codedecode.foodcatalagoue.entity.FoodItem;
import org.codedecode.foodcatalagoue.service.FoodItemService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FoodCatalogueControllerTest {

    @Mock
    private FoodItemService foodItemService;

    @InjectMocks
    private FoodCatalogueController foodCatalogueController;


    @Test
    void testAddFoodItem() {

        // Arrange

        // 1. Input DTO (new food item)
        FoodItemDTO inputFood = new FoodItemDTO(
                null,
                "Paneer Tikka",
                true,
                new BigDecimal("250.00"),
                1,
                1
        );

        // 2. Expected saved DTO
        FoodItemDTO savedFood = new FoodItemDTO(
                1,
                "Paneer Tikka",
                true,
                new BigDecimal("250.00"),
                1,
                1
        );

        // 3. Mock service behaviour
        when(foodItemService.addFood(inputFood))
                .thenReturn(savedFood);

        // Act

        ResponseEntity<FoodItemDTO> response =
                foodCatalogueController.addFoodItem(inputFood);

        // Assert

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals(1, response.getBody().getFoodId());

        assertEquals("Paneer Tikka", response.getBody().getItemName());

        assertEquals(new BigDecimal("250.00"),
                response.getBody().getPrice());

        verify(foodItemService).addFood(inputFood);
    }

    @Test
    void testFetchRestaurantDetailsWithFoodMenu() {
        //Arrange
        Integer restaurantId=1;

        FoodItem food1=new FoodItem(1,"Paneer Tikka",true,new BigDecimal("250.00"),1,1);
        FoodItem food2 = new FoodItem(
                2, "Chicken Biryani", false,
                new BigDecimal("300.00"), 1, 1
        );
        List<FoodItem> foodItems = Arrays.asList(food1, food2);
        Restaurant restaurant = new Restaurant(
                1, "Food Palace", "MG Road",
                "Pune", "Family Restaurant"
        );

        FoodCataloguePage mockPage =
                new FoodCataloguePage(foodItems, restaurant);
        when(foodItemService.fetchFoodCataloguePageDetails(restaurantId))
                .thenReturn(mockPage);
        // Act
        ResponseEntity<FoodCataloguePage> response =
                foodCatalogueController.fetchRestaurantDetailsWithFoodMenu(restaurantId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, response.getBody().getFoodItemsList().size());

        assertEquals("Paneer Tikka",
                response.getBody().getFoodItemsList().get(0).getItemName());

        assertEquals("Food Palace",
                response.getBody().getRestaurant().getName());

        verify(foodItemService).fetchFoodCataloguePageDetails(restaurantId);
    }

}
