package com.safiservices.customer;


import com.safiservices.amqp.RabbitMQMessageProducer;
import com.safiservices.clients.fraud.FraudCheckResponse;
import com.safiservices.clients.fraud.FraudClient;
import com.safiservices.clients.notification.NotificationClient;
import com.safiservices.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final FraudClient fraudClient;
    //private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void add(Customer customer){
        customerRepository.saveAndFlush(customer);

        System.out.println("customer id is "+customer.getId());
       //FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalArgumentException("not a good customer");
        }

//        notificationClient.sendNotification(new NotificationRequest(customer.getId(), customer.getEmail(), String.format("Hi %s, welcome to Amigoscode...",
//                customer.getFirstname())));

        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getEmail(), String.format("Hi %s, welcome to Amigoscode...",customer.getFirstname()));

        rabbitMQMessageProducer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");

    }
}
