package com.kitcha.interest.repository;

import com.kitcha.interest.entity.InterestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<InterestEntity, Long> {
    InterestEntity findByUserId(Long userId);
}
