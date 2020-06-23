package com.example.restfulwebservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 회원 정보 JpaRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
