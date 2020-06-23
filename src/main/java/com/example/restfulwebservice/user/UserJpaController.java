package com.example.restfulwebservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @Autowired
    private PostRepository postRepository;

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

    /**
     * 회원 ID로 회원 정보 삭제
     *
     * @param id
     * @throws UserNotFoundException
     */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable final int id) throws UserNotFoundException {
        this.userRepository.deleteById(id);
    }

    /**
     * 신규 회원 등록
     *
     * @param user
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody final User user) {
        final User createdUser = this.userRepository.save(user);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * 회원 번호로 등록된 게시물 정보 검색
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostByUser(@PathVariable final int id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("ID[%d] not found", id)))
                .getPosts();
    }

    /**
     * 게시물 등록
     *
     * @param id
     * @param post
     * @return
     */
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<User> createUser(@PathVariable final int id, @Valid @RequestBody final Post post) {
        final User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("ID[%d] not found", id)));

        post.setUser(user);

        final Post createdPost = this.postRepository.save(post);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
