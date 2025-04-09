package LootlyBackend.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private String clientSecret;
    private String paymentIntentId;
}
