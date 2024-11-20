package com.areastory.user.kafka;

import com.areastory.user.config.properties.KafkaProperties;
import com.areastory.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReplyListener {
    private final KafkaProperties kafkaProperties;
    private final UserService userService;

    @KafkaListener(id = "${kafka.group.user-reply}", topics = "${kafka.type.user-reply}", containerFactory = "userReplyContainerFactory")
    public void articleListen(Long userId) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        userService.validateUser(userId);
        System.out.println("validated : " + userId);
    }
}
