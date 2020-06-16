package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 회원 검색 결과가 존재하지 않는 경우 발생시킬 Exception 클래스
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    /**
     * 생성자
     *
     * @param message
     */
    public UserNotFoundException(final String message) {
        super(message);
    }
}
