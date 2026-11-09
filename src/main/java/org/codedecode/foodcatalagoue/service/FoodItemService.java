
package org.codedecode.foodcatalagoue.service;

import org.codedecode.foodcatalagoue.dto.FoodCataloguePage;
import org.codedecode.foodcatalagoue.dto.FoodItemDTO;
import org.codedecode.foodcatalagoue.dto.Restaurant;
import org.codedecode.foodcatalagoue.entity.FoodItem;
import org.codedecode.foodcatalagoue.mapper.FoodItemMapper;
import org.codedecode.foodcatalagoue.repository.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private RestTemplate restTemplate;

    public FoodItemDTO addFood(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemRepo.save(
                FoodItemMapper.INSTANCE.mapFoodItemDTOtoFoodItem(foodItemDTO)
        );

        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItem);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {

        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);

        Restaurant restaurant =
                fetchRestaurantDetailsFromRestaurantMS(restaurantId);

        return createFoodCataloguePage(foodItemList, restaurant);
    }


    private Restaurant fetchRestaurantDetailsFromRestaurantMS(
            Integer restaurantId) {

        String url = "http://RESTAURANT-SERVICE/restaurant/fetchByID/" + restaurantId;

        return restTemplate.getForObject(url, Restaurant.class);
    }


    private FoodCataloguePage createFoodCataloguePage(
            List<FoodItem> foodItemList,
            Restaurant restaurant) {

        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);

        return foodCataloguePage;
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
