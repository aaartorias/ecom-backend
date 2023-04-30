package com.artorias.ecommerce.service;

import com.artorias.ecommerce.dto.Purchase;
import com.artorias.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
