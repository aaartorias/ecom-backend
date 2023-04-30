package com.artorias.ecommerce.controller;

import com.artorias.ecommerce.dto.Purchase;
import com.artorias.ecommerce.dto.PurchaseResponse;
import com.artorias.ecommerce.service.CheckoutService;
import org.hibernate.annotations.Check;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = this.checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }
}
