
package org.codedecode.foodcatalagoue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {

    private Integer foodId;
    private String itemName;
    private boolean veg;
    private BigDecimal price;
    private Integer restaurantId;
    private Integer quantity;
}
