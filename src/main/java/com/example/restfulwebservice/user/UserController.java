package com.example.restfulwebservice.user;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
    public Resource<User> retrieveUser(@PathVariable final int id) {
        final User user = this.service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }

        // HATEOAS
        final Resource<User> resource = new Resource<>(user);
        final ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkBuilder.withRel("all-users"));

        return resource;
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
