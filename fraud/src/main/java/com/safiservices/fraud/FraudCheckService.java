package com.safiservices.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {

    private final FraudCheckRepository fraudCheckRepository;

    public FraudCheckService(FraudCheckRepository fraudCheckRepository) {
        this.fraudCheckRepository = fraudCheckRepository;
    }


    public boolean isFraudulentCustomer(Integer customerId){
        fraudCheckRepository.save(FraudCheckHistory.builder()
        .customerId(customerId)
        .isFraud(false)
        .createdAt(LocalDateTime.now())
        .build());
         
        return false;
    }
}
