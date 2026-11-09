package org.codedecode.foodcatalagoue.repository;

import org.codedecode.foodcatalagoue.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem,Integer> {

    List<FoodItem> findByRestaurantId(Integer restaurantId);

}
