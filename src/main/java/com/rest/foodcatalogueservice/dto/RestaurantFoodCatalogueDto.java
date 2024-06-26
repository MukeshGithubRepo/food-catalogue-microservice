package com.rest.foodcatalogueservice.dto;


import com.rest.foodcatalogueservice.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantFoodCatalogueDto {

    private List<FoodItem> foodItemsList;
    private RestaurantDto restaurant;

    public List<FoodItem> getFoodItemsList() {
        return foodItemsList;
    }

    public void setFoodItemsList(List<FoodItem> foodItemsList) {
        this.foodItemsList = foodItemsList;
    }

    public RestaurantDto getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDto restaurant) {
        this.restaurant = restaurant;
    }
}
