package org.codedecode.foodcatalagoue.mapper;

import org.codedecode.foodcatalagoue.dto.FoodItemDTO;
import org.codedecode.foodcatalagoue.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

    // foodItem to DTO
     FoodItem mapFoodItemDTOtoFoodItem(FoodItemDTO foodItemDTO);

     FoodItemDTO mapFoodItemToFoodItemDTO(FoodItem foodItem);


}
