package com.areastory.user.kafka;

import com.areastory.user.config.properties.KafkaProperties;
import com.areastory.user.dto.common.ArticleKafkaDto;
import com.areastory.user.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleListener {
    private final KafkaProperties kafkaProperties;
    private final ArticleService articleService;

    @KafkaListener(id = "${kafka.group.article}", topics = "${kafka.topic.article}", containerFactory = "articleContainerFactory")
    public void articleListen(ArticleKafkaDto articleKafkaDto) {
        String type = articleKafkaDto.getType();
        if (type.equals(kafkaProperties.getCommand().getInsert()))
            articleService.addArticle(articleKafkaDto);

        else if (type.equals(kafkaProperties.getCommand().getUpdate()))
            articleService.updateArticle(articleKafkaDto);

        else if (type.equals(kafkaProperties.getCommand().getDelete()))
            articleService.deleteArticle(articleKafkaDto);
    }
}
