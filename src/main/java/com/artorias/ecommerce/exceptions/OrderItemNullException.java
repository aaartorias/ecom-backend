package com.artorias.ecommerce.exceptions;

public class OrderItemNullException extends RuntimeException {
    static final String message = "Order item is null";
    public OrderItemNullException() {
        super(message);
    }

}