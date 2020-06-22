package com.example.restfulwebservice.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "회원 상세 정보")
@Entity
/**
 * 회원 정보 V1
 */
public class User {
    @Id // PK
    @GeneratedValue // PK 자동 생성
    private Integer id;

    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요") // 최소 2글자 이상 제한
    @ApiModelProperty(notes = "회원 이름을 입력해주세요")
    private String name;

    @Past // 과거의 날짜 제한
    @ApiModelProperty(notes = "회원 등록일을 입력해주세요")
    private Date joinDate;

    @ApiModelProperty(notes = "회원 비밀번호를 입력해주세요")
    private String password;

    @ApiModelProperty(notes = "회원 주민번호를 입력해주세요")
    private String ssn;
}
