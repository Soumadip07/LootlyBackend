package LootlyBackend.payloads;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
 
    private List<String> productNames;
    private Long amount; // Stripe needs amount in cents
    private String currency;
    private String email;
}
