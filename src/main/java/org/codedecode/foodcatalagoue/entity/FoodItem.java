
package org.codedecode.foodcatalagoue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "food_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodId;

    private String itemName;

    private boolean veg;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private Integer restaurantId;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer quantity = 0;
}
