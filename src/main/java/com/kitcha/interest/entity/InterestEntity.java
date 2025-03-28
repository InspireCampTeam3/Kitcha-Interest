package com.kitcha.interest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "interest")
@Data
public class InterestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String interest;
}
