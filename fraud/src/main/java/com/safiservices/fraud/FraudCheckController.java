package com.safiservices.fraud;


import com.safiservices.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/fraud-check")
@AllArgsConstructor
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;
    private static Logger logger = Logger.getLogger(FraudCheckController.class.getName());



    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") int customerId){
        boolean isFraudster = fraudCheckService.isFraudulentCustomer(customerId);
        logger.info("fraud checking customer "+customerId);
        return new FraudCheckResponse(isFraudster);
    }

}
