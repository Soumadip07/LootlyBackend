package LootlyBackend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import LootlyBackend.payloads.PaymentRequest;
import LootlyBackend.payloads.PaymentResponse;
import LootlyBackend.services.PaymentService;

@RestController
@RequestMapping("/api/stripe")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-checkout-session")
    public Map<String, Object> createCheckoutSession(@RequestBody PaymentRequest request) throws StripeException {
        Session session = paymentService.createCheckoutSession(
                request.getEmail(),
                request.getAmount(),
                request.getCurrency(),
                String.join(", ", request.getProductNames())
        );

        Map<String, Object> response = new HashMap<>();
        response.put("checkoutUrl", session.getUrl());
        response.put("sessionId", session.getId());
        return response;
    }
}
