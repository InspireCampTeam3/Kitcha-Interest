package com.kitcha.interest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KafkaInterestDto {
    private Schema schema;
    private Payload payload;
}
