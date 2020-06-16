package com.example.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
/**
 * 회원 정보 클래스
 */
public class User {
    private Integer id;
    private String name;
    private Date joinDate;

    /**
     * ID를 제외한 다른 데이터 수정
     *
     * @param other
     */
    public void update(final User other) {
        setName(other.getName());
        setJoinDate(other.getJoinDate());
    }
}
