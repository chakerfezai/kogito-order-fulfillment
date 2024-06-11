package com.sciam.kogito.order.service;

import com.sciam.kogito.order.entity.Payment;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class paymentService {


    public Payment create(Payment payment) {
        payment.persist();
        return payment;
    }

}
