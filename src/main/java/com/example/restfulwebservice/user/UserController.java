package com.example.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        final User user = this.service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }

        return user;
    }

    /**
     * 회원 등록
     *
     * @param user
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        final User savedUser = this.service.save(user);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * 회원 ID로 회원 정보 삭제
     *
     * @param id
     */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable final int id) {
        final User user = this.service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }
    }
}
