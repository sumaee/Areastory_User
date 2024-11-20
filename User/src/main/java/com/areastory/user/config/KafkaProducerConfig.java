package com.areastory.user.config;

import com.areastory.user.config.properties.KafkaProperties;
import com.areastory.user.dto.common.FollowKafkaDto;
import com.areastory.user.dto.common.NotificationKafkaDto;
import com.areastory.user.dto.common.UserKafkaDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {
    private final KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<Long, FollowKafkaDto> followProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, FollowKafkaDto> followTemplate() {
        return new KafkaTemplate<>(followProducerFactory());
    }

    @Bean
    public ProducerFactory<Long, UserKafkaDto> userProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, UserKafkaDto> userTemplate() {
        return new KafkaTemplate<>(userProducerFactory());
    }

    @Bean
    public ProducerFactory<Long, NotificationKafkaDto> notificationProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, NotificationKafkaDto> notificationTemplate() {
        return new KafkaTemplate<>(notificationProducerFactory());
    }

    @Bean
    public Map<String, Object> notificationProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getKafkaUrl());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getKafkaUrl());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }
}
