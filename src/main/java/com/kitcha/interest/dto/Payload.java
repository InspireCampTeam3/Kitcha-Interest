package com.kitcha.interest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payload {
    private Long user_id;
    private String interest;
}
