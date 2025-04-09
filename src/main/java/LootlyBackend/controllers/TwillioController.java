package LootlyBackend.controllers;


import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class TwillioController {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.service.id}")
    private String twilioServiceId;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }


    @PostMapping("/sendOtp")
    public String sendVerificationCode(@RequestParam String phoneNumber) {
        Verification verification = Verification.creator(twilioServiceId, phoneNumber, "sms").create();
        return verification.getStatus();

    }

    @PostMapping("/validateOtp")
    public String validateVerificationCode(@RequestParam String phoneNumber, @RequestParam String verificationCode) {
        VerificationCheck verificationCheck = VerificationCheck.creator(
                twilioServiceId)
                .setTo(phoneNumber)
                .setCode(verificationCode)
                .create();

        return verificationCheck.getStatus();


    }
}