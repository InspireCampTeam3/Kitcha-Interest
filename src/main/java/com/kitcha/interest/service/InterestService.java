package com.kitcha.interest.service;

import com.kitcha.interest.dto.InterestDto;
import com.kitcha.interest.entity.InterestEntity;
import com.kitcha.interest.exception.UserIdNotFoundException;
import com.kitcha.interest.repository.InterestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;

    public void setInterest(Long userId, InterestDto dto) throws RuntimeException {
        InterestEntity interestEntity = interestRepository.findByUserId(userId);
        if (interestEntity == null) {
            interestEntity = new InterestEntity();
            interestEntity.setUserId(userId);
        }

        interestEntity.setInterest(dto.getInterest());
        interestRepository.save(interestEntity);
    }

    public String getInterest(Long userId) {
        InterestEntity interestEntity = interestRepository.findByUserId(userId);
        if (interestEntity == null) {
            throw new UserIdNotFoundException("올바른 사용자가 아닙니다.");
        }

        return interestEntity.getInterest();
    }
}
