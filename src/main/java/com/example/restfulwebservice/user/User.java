package com.example.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
/**
 * 회원 정보 DTO 클래스
 */
public class User {
    private Integer id;
    private String name;
    private Date joinDate;
}
