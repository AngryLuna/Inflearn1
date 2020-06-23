package com.example.restfulwebservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 게시물 정보 JpaRepository
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
