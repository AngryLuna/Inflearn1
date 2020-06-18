package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"}) // password, ssn 필드는 응답시에 필터링 처리
/**
 * 회원 정보 클래스
 */
public class User {
    private Integer id;

    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요") // 최소 2글자 이상 제한
    private String name;

    @Past // 과거의 날짜 제한
    private Date joinDate;

//    @JsonIgnore // 응답시 필터링
    private String password;

//    @JsonIgnore // 응답시 필터링
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
