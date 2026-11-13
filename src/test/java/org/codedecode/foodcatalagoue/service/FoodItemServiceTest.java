package org.codedecode.foodcatalagoue.service;

import org.codedecode.foodcatalagoue.dto.FoodCataloguePage;
import org.codedecode.foodcatalagoue.dto.FoodItemDTO;
import org.codedecode.foodcatalagoue.dto.Restaurant;
import org.codedecode.foodcatalagoue.entity.FoodItem;
import org.codedecode.foodcatalagoue.repository.FoodItemRepo;
import org.springframework.web.client.RestTemplate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodItemServiceTest {

    @Mock
    private FoodItemRepo foodItemRepo;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FoodItemService foodItemService;

    @Test
    void testAddFood() {

        // Arrange
        FoodItemDTO inputFood = new FoodItemDTO(
                null,
                "Paneer Tikka",
                true,
                new BigDecimal("250.00"),
                1,
                1
        );

        FoodItem savedFood = new FoodItem(
                1,
                "Paneer Tikka",
                true,
                new BigDecimal("250.00"),
                1,
                1
        );

        when(foodItemRepo.save(any(FoodItem.class)))
                .thenReturn(savedFood);

        // Act
        FoodItemDTO result = foodItemService.addFood(inputFood);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getFoodId());
        assertEquals("Paneer Tikka", result.getItemName());
        assertEquals(new BigDecimal("250.00"), result.getPrice());

        verify(foodItemRepo, times(1)).save(any(FoodItem.class));
    }

    @Test
    void testFetchFoodCataloguePageDetails() {

        // Arrange
        Integer restaurantId = 1;

        FoodItem food1 = new FoodItem(
                1,
                "Paneer Tikka",
                true,
                new BigDecimal("250.00"),
                1,
                1
        );

        FoodItem food2 = new FoodItem(
                2,
                "Chicken Biryani",
                false,
                new BigDecimal("300.00"),
                1,
                1
        );

        List<FoodItem> foodItems = Arrays.asList(food1, food2);

        Restaurant restaurant = new Restaurant(
                1,
                "Food Palace",
                "MG Road",
                "Pune",
                "Family Restaurant"
        );

        String url =
                "http://RESTAURANT-SERVICE/restaurant/fetchByID/1";

        // Mock repository response
        when(foodItemRepo.findByRestaurantId(restaurantId))
                .thenReturn(foodItems);

        // Mock REST API response
        when(restTemplate.getForObject(url, Restaurant.class))
                .thenReturn(restaurant);

        // Act
        FoodCataloguePage result =
                foodItemService.fetchFoodCataloguePageDetails(restaurantId);

        // Assert
        assertNotNull(result);

        assertEquals(2, result.getFoodItemsList().size());
        assertEquals("Paneer Tikka",
                result.getFoodItemsList().get(0).getItemName());

        assertEquals("Food Palace",
                result.getRestaurant().getName());

        // Verify dependency calls
        verify(foodItemRepo, times(1))
                .findByRestaurantId(restaurantId);

        verify(restTemplate, times(1))
                .getForObject(url, Restaurant.class);
    }
}