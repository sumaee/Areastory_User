package com.areastory.user.kafka;

import com.areastory.user.config.properties.KafkaProperties;
import com.areastory.user.db.entity.UserInfo;
import com.areastory.user.dto.common.UserKafkaDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProducer {
    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate<Long, UserKafkaDto> userTemplate;

    public void send(UserInfo user, String type) {
        UserKafkaDto userKafkaDto = UserKafkaDto.builder()
                .type(type)
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .profile(user.getProfile())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .build();
        System.out.println(userKafkaDto);
        userTemplate.send(new ProducerRecord<>(kafkaProperties.getTopic().getUser(), userKafkaDto.getUserId(), userKafkaDto));
    }
}
