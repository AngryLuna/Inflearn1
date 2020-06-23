package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 게시물 정보
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY) // Post : User = 1 : N 관계, 지연 로딩 방식 (user 필드에 최초 접근시 세팅)
    @JsonIgnore
    private User user;
}
