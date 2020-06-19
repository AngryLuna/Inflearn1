package com.example.restfulwebservice.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel retrieveUser(@PathVariable final int id) {
        final User user = this.service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }

        // HATEOAS
        final WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        final Link builder = linkTo.withRel("all-users");

        return EntityModel.of(user, builder);
    }

    /**
     * 회원 등록
     *
     * @param user
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) { // 회원 정보 데이터 유효성 체크
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
