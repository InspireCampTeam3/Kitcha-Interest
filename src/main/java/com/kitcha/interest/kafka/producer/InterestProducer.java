package com.kitcha.interest.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitcha.interest.dto.Field;
import com.kitcha.interest.dto.KafkaInterestDto;
import com.kitcha.interest.dto.Payload;
import com.kitcha.interest.dto.Schema;
import com.kitcha.interest.entity.InterestEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class InterestProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    List<Field> fields = Arrays.asList(
            new Field("int64", true, "user_id"),
            new Field("string", true, "interest")
    );

    Schema schema = Schema.builder()
            .type("struct")
            .fields(fields)
            .optional(false)
            .name("file")
            .build();

    @Autowired
    public InterestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public InterestEntity send(String topic, InterestEntity interest) {
        Payload payload = Payload.builder()
                .user_id(interest.getUserId())
                .interest(interest.getInterest())
                .build();

        KafkaInterestDto kafkaInterestDto = new KafkaInterestDto(schema, payload);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = objectMapper.writeValueAsString(kafkaInterestDto);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Sent interest to topic {}", topic);

        return interest;
    }
}
