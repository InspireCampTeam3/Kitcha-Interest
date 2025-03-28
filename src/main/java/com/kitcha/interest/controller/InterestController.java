package com.kitcha.interest.controller;

import com.kitcha.interest.dto.InterestDto;
import com.kitcha.interest.service.InterestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.util.Collections.singletonMap;

@RestController
@RequiredArgsConstructor
public class InterestController {
    private final InterestService interestService;

    @GetMapping("/interest")
    public ResponseEntity<Map<String, String>> interest(@RequestHeader("X-User-Id") String userId) {
        String interest = interestService.getInterest(Long.parseLong(userId));

        return ResponseEntity.ok(singletonMap("interest", interest));
    }

    @PostMapping("/interest")
    public ResponseEntity<Map<String, String>> setInterest(@RequestHeader("X-User-Id") String userId, @Valid @RequestBody InterestDto dto) {
        interestService.setInterest(Long.parseLong(userId), dto);

        return ResponseEntity.ok(singletonMap("message", "관심사 설정 성공"));
    }
}
