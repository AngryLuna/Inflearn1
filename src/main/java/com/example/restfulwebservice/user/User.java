package com.example.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
/**
 * 회원 정보 클래스
 */
public class User {
    private Integer id;

    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요") // 최소 2글자 이상 제한
    private String name;

    @Past // 과거의 날짜 제한
    private Date joinDate;

    private String password;

    private String ssn;

    /**
     * ID를 제외한 다른 데이터 수정
     *
     * @param other
     */
    public void update(final User other) {
        setName(other.getName());
        setJoinDate(other.getJoinDate());
        setPassword(other.getPassword());
        setSsn(other.getSsn());
    }
}
