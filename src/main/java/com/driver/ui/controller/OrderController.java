package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.OrderService;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;
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
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderServiceImpl orderServiceImpl;
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{

		OrderDto orderDto = orderServiceImpl.getOrder(id);

		OrderDetailsResponse response = new OrderDetailsResponse();

		response.setOrderId(orderDto.getOrderId());
		response.setItems(orderDto.getItems());
		response.setCost(orderDto.getCost());
		response.setOrderId(orderDto.getOrderId());
		response.setStatus(orderDto.isStatus());

		return response;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {

		OrderDto orderDto = orderServiceImpl.createOrder(order);

		OrderDetailsResponse response = new OrderDetailsResponse();

		response.setOrderId(orderDto.getOrderId());
		response.setItems(orderDto.getItems());
		response.setCost(orderDto.getCost());
		response.setOrderId(orderDto.getOrderId());
		response.setStatus(orderDto.isStatus());

		return response;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{

		OrderDto orderDto = orderServiceImpl.updateOrder(id, order);

		OrderDetailsResponse response = new OrderDetailsResponse();

		response.setOrderId(orderDto.getOrderId());
		response.setItems(orderDto.getItems());
		response.setCost(orderDto.getCost());
		response.setOrderId(orderDto.getOrderId());
		response.setStatus(orderDto.isStatus());

		return response;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {


		try {
			orderServiceImpl.deleteOrder(id);
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
	public List<OrderDetailsResponse> getOrders() {

		List<OrderDto> responseDto = orderServiceImpl.getOrders();

		List<OrderDetailsResponse> ans = new ArrayList<>();
		for(OrderDto orderDto : responseDto) {

			OrderDetailsResponse response = new OrderDetailsResponse();
			response.setOrderId(orderDto.getOrderId());
			response.setItems(orderDto.getItems());
			response.setCost(orderDto.getCost());
			response.setOrderId(orderDto.getOrderId());
			response.setStatus(orderDto.isStatus());

			ans.add(response);
		}
		return ans;
	}
}
