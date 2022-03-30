package com.safiservices.notification;

import com.safiservices.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping
    void sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("New notification ...{}", notificationRequest);
        notificationService.save(notificationRequest);
    }

}
