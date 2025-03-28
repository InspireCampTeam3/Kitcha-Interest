package com.kitcha.interest.exception;

import com.kitcha.interest.controller.InterestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static java.util.Collections.singletonMap;

@ControllerAdvice(basePackageClasses = InterestController.class)
public class InterestControllerExceptionHandler {

    // @Valid로 인한 검증 에러 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(singletonMap("message", e.getMessage()));
    }

    // MissingRequestHeaderException 처리 (setInterest)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Map<String, String>> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(singletonMap("message", "로그인 후 이용해주세요."));
    }

    // UserIdNotFoundException 처리 (setInterest, getInterest)
    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserIdNotFoundException(UserIdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(singletonMap("message", e.getMessage()));
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(singletonMap("message", "서버 오류가 발생하였습니다."));
    }


}
