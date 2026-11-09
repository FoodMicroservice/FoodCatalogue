package org.codedecode.foodcatalagoue.controller;

import org.codedecode.foodcatalagoue.dto.FoodCataloguePage;
import org.codedecode.foodcatalagoue.dto.FoodItemDTO;
import org.codedecode.foodcatalagoue.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {
    @Autowired
    private FoodItemService foodItemService;

    //add food
    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
     FoodItemDTO savedFood=foodItemService.addFood(foodItemDTO);
     return new ResponseEntity<>(savedFood,HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurantDetailsWithFoodMenu(
            @PathVariable("restaurantId") Integer restaurantId) {

        FoodCataloguePage page =
                foodItemService.fetchFoodCataloguePageDetails(restaurantId);

        return ResponseEntity.ok(page);
    }
}
