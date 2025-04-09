package LootlyBackend.services.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;

import LootlyBackend.payloads.PaymentRequest;
import LootlyBackend.payloads.PaymentResponse;
import LootlyBackend.services.PaymentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

	 @Value("${stripe.secret.key}")
	    private String secretKey;
	
	 @Override
	    public Session createCheckoutSession(String email, Long amount, String currency, String productName) throws com.stripe.exception.StripeException {
	        Stripe.apiKey = secretKey;

	        SessionCreateParams params = SessionCreateParams.builder()
	                .setMode(SessionCreateParams.Mode.PAYMENT)
	                .setSuccessUrl("http://localhost:5173/payment-success")
	                .setCancelUrl("http://localhost:5173/payment-failed")
	                .addLineItem(
	                        SessionCreateParams.LineItem.builder()
	                                .setQuantity(1L)
	                                .setPriceData(
	                                        SessionCreateParams.LineItem.PriceData.builder()
	                                                .setCurrency(currency)
	                                                .setUnitAmount(amount)
	                                                .setProductData(
	                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
	                                                                .setName(productName)
	                                                                .build()
	                                                )
	                                                .build()
	                                )
	                                .build()
	                )
	                .setCustomerEmail(email)
	                .build();

	        return Session.create(params);
	    }
	}

