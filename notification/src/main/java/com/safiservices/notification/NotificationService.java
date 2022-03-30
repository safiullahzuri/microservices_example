package com.safiservices.notification;

import com.safiservices.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void save(NotificationRequest request){
        notificationRepository.save(Notification.builder()
        .toCustomerId(request.getCustomerId())
        .toCustomerEmail(request.getToCustomerName())
        .sender("safi")
        .message(request.getMessage())
        .sentAt(LocalDateTime.now())
        .build());
    }
}
