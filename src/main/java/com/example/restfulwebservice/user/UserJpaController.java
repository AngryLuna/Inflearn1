package com.example.restfulwebservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * JPA를 사용하는 회원 정보 RestController
 */
@RestController
@RequestMapping("/jpa")
public class UserJpaController {
    @Autowired
    private UserRepository userRepository;

    /**
     * 전체 회원 정보 검색
     *
     * @return
     */
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * 회원 ID로 회원 정보 검색
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable final int id) throws UserNotFoundException {
        final User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("ID[%d] not found", id)));

        // HATEOAS
        final Resource<User> resource = new Resource<>(user);
        resource.add(linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users"));

        return resource;
    }
}
