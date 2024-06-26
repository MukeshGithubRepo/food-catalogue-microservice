package com.rest.foodcatalogueservice.service;



import com.rest.foodcatalogueservice.dto.RestaurantDto;
import com.rest.foodcatalogueservice.dto.RestaurantFoodCatalogueDto;
import com.rest.foodcatalogueservice.entity.FoodItem;
import com.rest.foodcatalogueservice.exceptions.UserNotFoundException;
import com.rest.foodcatalogueservice.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodCatalogueService {


    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;


    public FoodItem addFoodItem(FoodItem foodItem) {
        FoodItem savedFoodItem = foodItemRepo.save(foodItem);
        return savedFoodItem;
    }


    public RestaurantFoodCatalogueDto fetchRestaurantFoodCatalogueById(int restaurantId) {
        //food item list from this current microservice and current db
        List<FoodItem> foodItems= fetchFoodItemsListByRestaurantId(restaurantId);

        //fetch restaurant details from RestaurantListApp microservice by restaurntid
        RestaurantDto restaurant = fetchRestaurantDetailsfromRestaurantMicroService(restaurantId);

        //combine both fooditems with restaurant and create RestaurantFoodCatalogueDto
        return createRestaurantFoodCatalogueDto(foodItems,restaurant);


    }

    private List<FoodItem> fetchFoodItemsListByRestaurantId(int restaurantId)
    {
            List<FoodItem> retrivedFoodItems = foodItemRepo.findByRestaurantId(restaurantId);

            if(retrivedFoodItems != null)
            {
                return retrivedFoodItems;
            }
            else {
                throw new UserNotFoundException("Restaurant not found with restaurantId:"+restaurantId);
            }
    }


    private RestaurantDto fetchRestaurantDetailsfromRestaurantMicroService(int restaurantId)
    {

        //HttpEntity httpEntity=new HttpEntity();
            //restTemplate.exchange()
            RestaurantDto fetchedRestaurant =restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/"+restaurantId, RestaurantDto.class);
            return fetchedRestaurant;
    }


//    public HttpHeaders setHttpHeaders()
//    {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth();
//    }

    private RestaurantFoodCatalogueDto createRestaurantFoodCatalogueDto(List<FoodItem> foodItems,RestaurantDto restaurant)
    {
            RestaurantFoodCatalogueDto restaurantFoodCatalogueDto=new RestaurantFoodCatalogueDto();
            restaurantFoodCatalogueDto.setFoodItemsList(foodItems);
            restaurantFoodCatalogueDto.setRestaurant(restaurant);

            return restaurantFoodCatalogueDto;
    }

}
