package com.artorias.ecommerce.controller;

import com.artorias.ecommerce.dto.Purchase;
import com.artorias.ecommerce.dto.PurchaseResponse;
import com.artorias.ecommerce.service.CheckoutService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Check;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@Slf4j
public class CheckoutController {
    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        log.info("placing order {}", purchase);
        PurchaseResponse purchaseResponse = this.checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }
}
