package com.example.restfulwebservice.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 회원 정보 처리 RestController
 */
@RestController
public class UserController {
    private final UserDaoService service;

    /**
     * 생성자
     *
     * @param service
     */
    public UserController(final UserDaoService service) {
        this.service = service;
    }

    /**
     * 전체 회원 정보 검색
     *
     * @return
     */
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return this.service.findAll();
    }

    /**
     * 회원 ID로 회원 정보 검색
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable final int id) {
        return this.service.findOne(id);
    }

    /**
     * 회원 등록
     *
     * @param user
     */
    @PostMapping("/users")
    public void createUser(@RequestBody final User user) {
        final User savedUser = this.service.save(user);
    }
}
