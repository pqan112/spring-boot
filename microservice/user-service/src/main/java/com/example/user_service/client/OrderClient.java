package com.example.user_service.client;

import com.example.user_service.config.FeignClientInterceptorConfig;
import com.example.user_service.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service", configuration = FeignClientInterceptorConfig.class)
public interface OrderClient {

    @GetMapping("/api/orders/by-user/{userId}")
    List<OrderDto> getOrdersByUserId(@PathVariable("userId") Long userId);
}
