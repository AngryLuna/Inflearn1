package com.example.restfulwebservice.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "user") // User : Post = 1 : N
    private List<Post> posts;

    /**
     * 생성자 (JPA 사용하지 않는 테스트코드 오류 회피용)
     *
     * @param id
     * @param name
     * @param joinDate
     * @param password
     * @param ssn
     */
    public User(final int id, final String name, final Date joinDate, final String password, final String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
