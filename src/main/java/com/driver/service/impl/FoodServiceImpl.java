package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FoodServiceImpl {
    @Autowired
    FoodRepository foodRepository;
    public FoodDto getFood(String id) throws Exception {
        FoodEntity food = foodRepository.findByFoodId(id);

        if (food == null)
            throw new Exception("Food Not Found");

        // food to DTO

        FoodDto foodDto = new FoodDto();

        foodDto.setId(food.getId());
        foodDto.setFoodId(food.getFoodId());
        foodDto.setFoodCategory(food.getFoodCategory());
        foodDto.setFoodName(food.getFoodName());
        foodDto.setFoodPrice(food.getFoodPrice());

        return foodDto;
    }

    public FoodDto createFood(FoodDetailsRequestModel foodDetails) {
        // create food object and set the value using foodDetails

        FoodEntity food = new FoodEntity();

        food.setFoodId(String.valueOf(UUID.randomUUID()));
        food.setFoodName(foodDetails.getFoodName());
        food.setFoodCategory(foodDetails.getFoodCategory());
        food.setFoodPrice(foodDetails.getFoodPrice());

        FoodEntity savedFood = foodRepository.save(food);

        // foodEntity to dto response

        FoodDto foodDto = new FoodDto();

        foodDto.setId(savedFood.getId());
        foodDto.setFoodId(savedFood.getFoodId());
        foodDto.setFoodCategory(savedFood.getFoodCategory());
        foodDto.setFoodName(savedFood.getFoodName());
        foodDto.setFoodPrice(savedFood.getFoodPrice());

        return foodDto;

    }

    public FoodDto updateFood(String id, FoodDetailsRequestModel foodDetails) throws Exception {

        FoodEntity food = foodRepository.findByFoodId(id);

        if (food == null)
            throw new Exception("Food Not Found");

        FoodDto foodDto = new FoodDto();

        foodDto.setId(food.getId());
        foodDto.setFoodId(id);
        foodDto.setFoodCategory(food.getFoodCategory());
        foodDto.setFoodName(food.getFoodName());
        foodDto.setFoodPrice(food.getFoodPrice());

        return foodDto;
    }

    public void deleteFood(String id) throws Exception {
        try{
            FoodEntity response = foodRepository.findByFoodId(id);
            foodRepository.delete(response);
        }
        catch (Exception e){
            throw new Exception("Food Not Found");
        }
    }

    public List<FoodDto> getFoods() {
        List<FoodEntity> foodEntities = (List<FoodEntity>) foodRepository.findAll();

        List<FoodDto> foodDtosList = new ArrayList<>();
        for(FoodEntity food : foodEntities) {
            FoodDto foodDto = new FoodDto();

            foodDto.setId(food.getId());
            foodDto.setFoodId(foodDto.getFoodId());
            foodDto.setFoodCategory(food.getFoodCategory());
            foodDto.setFoodName(food.getFoodName());
            foodDto.setFoodPrice(food.getFoodPrice());

            foodDtosList.add(foodDto);
        }
        return foodDtosList;
    }
}