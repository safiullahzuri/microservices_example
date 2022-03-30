package com.safiservices.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationRequest {

    Integer customerId;
    String toCustomerName;
    String message;

}
