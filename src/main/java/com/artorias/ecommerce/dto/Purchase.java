package com.artorias.ecommerce.dto;

import com.artorias.ecommerce.entity.Address;
import com.artorias.ecommerce.entity.Customer;
import com.artorias.ecommerce.entity.Order;
import com.artorias.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
