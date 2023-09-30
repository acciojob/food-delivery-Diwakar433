package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.model.response.UserResponse;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
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
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{

		UserDto userDto = userServiceImpl.getUserByUserId(id);

		UserResponse userResponse = new UserResponse();
		userResponse.setEmail(userDto.getEmail());
		userResponse.setUserId(userDto.getUserId());
		userResponse.setFirstName(userDto.getFirstName());
		userResponse.setLastName(userDto.getLastName());

		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = userServiceImpl.createUser(userDetails);

		UserResponse response = new UserResponse();
		response.setUserId(userDto.getUserId());
		response.setEmail(userDto.getEmail());
		response.setFirstName(userDto.getFirstName());
		response.setLastName(userDto.getLastName());

		return response;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = userServiceImpl.updateUser(id, userDetails);

		UserResponse response = new UserResponse();
		response.setUserId(userDto.getUserId());
		response.setEmail(userDto.getEmail());
		response.setFirstName(userDto.getFirstName());
		response.setLastName(userDto.getLastName());

		return response;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{

		try{
			userServiceImpl.deleteUser(id);
			OperationStatusModel operationStatusModel = new OperationStatusModel();
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
			return operationStatusModel;
		}
		catch (Exception e){
			OperationStatusModel operationStatusModel = new OperationStatusModel();
			operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
			operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.toString());
			return operationStatusModel;
		}
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){

		List<UserDto> userDtoList = userServiceImpl.getUsers();

		List<UserResponse> ans = new ArrayList<>();

		for (UserDto x : userDtoList ) {

			UserResponse response = new UserResponse();

			response.setUserId(x.getUserId());
			response.setEmail(x.getEmail());
			response.setFirstName(x.getFirstName());
			response.setLastName(x.getLastName());

			ans.add(response);
		}
		return ans;
	}
	
}
