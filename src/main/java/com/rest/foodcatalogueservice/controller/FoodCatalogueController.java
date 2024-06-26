package com.rest.foodcatalogueservice.controller;


import com.rest.foodcatalogueservice.dto.RestaurantFoodCatalogueDto;
import com.rest.foodcatalogueservice.entity.FoodItem;
import com.rest.foodcatalogueservice.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
//@RequestMapping("/foodcatalogue")
public class FoodCatalogueController {

    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("/addfooditem")
    public ResponseEntity<FoodItem> addFoodItem(@RequestBody FoodItem foodItem) {

        FoodItem foodItem1=foodCatalogueService.addFoodItem(foodItem);
        return new ResponseEntity<>(foodItem1, HttpStatus.CREATED);
    }

    @GetMapping("/getRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<RestaurantFoodCatalogueDto> getRestaurantAndFoodItemsById(@PathVariable int restaurantId) {

        RestaurantFoodCatalogueDto foodCatalogueDto=foodCatalogueService.fetchRestaurantFoodCatalogueById(restaurantId);

        return new ResponseEntity<>(foodCatalogueDto,HttpStatus.OK);

    }

}
