package com.areastory.user.kafka;

import com.areastory.user.config.properties.KafkaProperties;
import com.areastory.user.db.entity.Follow;
import com.areastory.user.db.entity.UserInfo;
import com.areastory.user.dto.common.NotificationKafkaDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<Long, NotificationKafkaDto> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public void send(Follow follow) {
        UserInfo followerUser = follow.getFollowerUser();
        UserInfo followingUser = follow.getFollowingUser();
        NotificationKafkaDto followNotificationKafkaDto = NotificationKafkaDto.builder()
                .type(kafkaProperties.getGroup().getFollow())
                .userId(followingUser.getUserId())
                .username(followingUser.getNickname())
                .otherUserId(followerUser.getUserId())
                .otherUsername(followerUser.getNickname())
                .createdAt(follow.getCreatedAt())
                .build();
        kafkaTemplate.send(new ProducerRecord<>(kafkaProperties.getTopic().getNotification(), followerUser.getUserId(), followNotificationKafkaDto));
    }
}
