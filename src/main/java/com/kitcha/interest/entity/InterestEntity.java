package com.kitcha.interest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "interest")
@Data
public class InterestEntity {
    @Id
    private Long userId;

    private String interest;
}
