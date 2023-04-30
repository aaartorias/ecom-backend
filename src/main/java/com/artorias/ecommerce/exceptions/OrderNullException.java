package com.artorias.ecommerce.exceptions;

public class OrderNullException extends RuntimeException {
    private static final String message = "order is null";
    public OrderNullException() {
        super(message);
    }
}
