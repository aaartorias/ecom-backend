package com.artorias.ecommerce.service;

import com.artorias.ecommerce.dao.CustomerRepository;
import com.artorias.ecommerce.dto.Purchase;
import com.artorias.ecommerce.dto.PurchaseResponse;
import com.artorias.ecommerce.entity.Customer;
import com.artorias.ecommerce.entity.Order;
import com.artorias.ecommerce.entity.OrderItem;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

//    @Autowired - since there's only 1 constructor, spring will manage it without mentioning Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        log.info("placing order {}", purchase);

        Order order = purchase.getOrder();

        String orderTrackingNumber = this.generateOrderTrackingOrderNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(orderItem -> {
            order.addOrderItem(orderItem);
            orderItem.setOrder(order);
        });

        order.setShippingAddress(purchase.getShippingAddress());
        order.setBillingAddress(purchase.getBillingAddress());

        Customer customer = purchase.getCustomer();
        customer.addOrder(order);

        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingOrderNumber() {
        return UUID.randomUUID().toString();
    }
}
