package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl{

    @Autowired
    OrderRepository orderRepository;
    public OrderDto createOrder(OrderDetailsRequestModel order) {

        OrderEntity orderEntity = new OrderEntity();
        // set data to orderEntity
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setStatus(true);
        orderEntity.setUserId(order.getUserId());
//        save database
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        // saveOrder to orderDto

        OrderDto orderDto = new OrderDto();

        orderDto.setId(savedOrder.getId());
        orderDto.setOrderId(savedOrder.getOrderId());
        orderDto.setCost(savedOrder.getCost());
        orderDto.setItems(savedOrder.getItems());
        orderDto.setStatus(savedOrder.isStatus());

        return orderDto;


    }

    public OrderDto getOrder(String id) throws Exception {

        OrderEntity order = orderRepository.findByOrderId(id);

        if(order == null)
            throw new Exception("Order Not Found");

        //set to OrderDto

        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setOrderId(order.getOrderId());
        orderDto.setCost(order.getCost());
        orderDto.setItems(order.getItems());
        orderDto.setStatus(order.isStatus());

        return orderDto;

    }

    public OrderDto updateOrder(String id, OrderDetailsRequestModel order) throws Exception {

        OrderEntity orderEntity = orderRepository.findByOrderId(id);

        if(order == null)
            throw new Exception("Order Not Found");

        //set to OrderDto

        OrderDto orderDto = new OrderDto();

        orderDto.setId(orderEntity.getId());
        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setCost(orderEntity.getCost());
        orderDto.setItems(orderEntity.getItems());
        orderDto.setStatus(orderEntity.isStatus());

        return orderDto;

    }

    public void deleteOrder(String id) throws Exception {
        try{
            OrderEntity response = orderRepository.findByOrderId(id);
            orderRepository.delete(response);
        }
        catch (Exception e){
            throw new Exception("Food Not Found");
        }
    }

    public List<OrderDto> getOrders() {

        List<OrderEntity> orderEntities = (List<OrderEntity>) orderRepository.findAll();

        List<OrderDto> dtosList = new ArrayList<>();

        for (OrderEntity order : orderEntities) {

            OrderDto orderDto = new OrderDto();

            orderDto.setId(order.getId());
            orderDto.setOrderId(order.getOrderId());
            orderDto.setCost(order.getCost());
            orderDto.setItems(order.getItems());
            orderDto.setStatus(order.isStatus());

            dtosList.add(orderDto);
        }

        return dtosList;
    }
}