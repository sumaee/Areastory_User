package com.areastory.user.kafka;

import com.areastory.user.config.properties.KafkaProperties;
import com.areastory.user.db.entity.Follow;
import com.areastory.user.dto.common.FollowKafkaDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowProducer {
    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate<Long, FollowKafkaDto> followTemplate;

    public void send(Follow follow, String type) {
        FollowKafkaDto followKafkaDto = FollowKafkaDto.builder()
                .type(type)
                .followUserId(follow.getFollowerUser().getUserId())
                .followingUserId(follow.getFollowingUser().getUserId())
                .createdAt(follow.getCreatedAt())
                .build();
        followTemplate.send(new ProducerRecord<>(kafkaProperties.getTopic().getFollow(), followKafkaDto.getFollowUserId(), followKafkaDto));
    }
}
