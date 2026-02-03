package com.example.user_service.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseWithOrders {
    Long id;
    String name;
    String email;
    List<OrderDto> orders;
}
