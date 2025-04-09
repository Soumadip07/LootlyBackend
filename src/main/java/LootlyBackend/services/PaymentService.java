package LootlyBackend.services;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import LootlyBackend.payloads.PaymentRequest;
import LootlyBackend.payloads.PaymentResponse;

public interface PaymentService {
    Session createCheckoutSession(String email, Long amount, String currency, String productName) throws StripeException;
}
