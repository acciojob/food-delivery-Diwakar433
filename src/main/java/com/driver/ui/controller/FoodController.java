package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.FoodService;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl foodServiceImpl;
	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto = foodServiceImpl.getFood(id);

		FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();

		foodDetailsResponse.setFoodId(foodDto.getFoodId());
		foodDetailsResponse.setFoodName(foodDto.getFoodName());
		foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
		foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());

		return foodDetailsResponse;

	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {

		FoodDto foodDto = foodServiceImpl.createFood(foodDetails);

		FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();

		foodDetailsResponse.setFoodId(foodDto.getFoodId());
		foodDetailsResponse.setFoodName(foodDto.getFoodName());
		foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
		foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());

		return foodDetailsResponse;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{

		FoodDto foodDto = foodServiceImpl.updateFood(id, foodDetails);

		FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();

		foodDetailsResponse.setFoodId(foodDto.getFoodId());
		foodDetailsResponse.setFoodName(foodDto.getFoodName());
		foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
		foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());

		return foodDetailsResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{

		try {
			foodServiceImpl.deleteFood(id);
			OperationStatusModel operationStatusModel = new OperationStatusModel();
			operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
			operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
			return operationStatusModel;
		}catch (Exception e){
			OperationStatusModel operationStatusModel = new OperationStatusModel();
			operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
			operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.name());


			return operationStatusModel;
		}
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {

		List<FoodDto> responses = foodServiceImpl.getFoods();

		List<FoodDetailsResponse> ans = new ArrayList<>();

		for(FoodDto x: responses){
			FoodDetailsResponse temp = new FoodDetailsResponse();

			temp.setFoodPrice(x.getFoodPrice());
			temp.setFoodCategory(x.getFoodCategory());
			temp.setFoodId(x.getFoodId());
			temp.setFoodName(x.getFoodName());

			ans.add(temp);
		}

		return ans;
	}
}
