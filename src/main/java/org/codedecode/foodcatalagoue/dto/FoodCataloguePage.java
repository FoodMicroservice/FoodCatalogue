package org.codedecode.foodcatalagoue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codedecode.foodcatalagoue.entity.FoodItem;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {

    private List<FoodItem> foodItemsList;

    private Restaurant restaurant;
}
