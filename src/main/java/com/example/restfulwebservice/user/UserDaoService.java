package com.example.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 회원 정보 비즈니스 로직 처리 클래스 (DAO, Service 클래스 통합)
 */
public class UserDaoService {
    /**
     * DB로 가정할 리스트
     */
    private static final List<User> userList = new ArrayList<>();

    /**
     * 회원 번호 (시퀀스라고 가정)
     */
    private static int userCount = 0;

    static {
        // DB에 3명의 유저가 등록되어있다고 가정
        userList.add(new User(getNextUserId(), "TestUser1", new Date()));
        userList.add(new User(getNextUserId(), "TestUser2", new Date()));
        userList.add(new User(getNextUserId(), "TestUser3", new Date()));
    }

    /**
     * 다음에 등록할 회원 Id 리턴 (시퀀스라고 가정)
     *
     * @return
     */
    private static int getNextUserId() {
        return ++userCount;
    }

    /**
     * 전체 회원 검색
     *
     * @return
     */
    public List<User> findAll() {
        return userList;
    }

    /**
     * id로 1명의 회원 검색
     *
     * @param id
     * @return
     */
    public User findOne(final int id) {
        return userList.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * 새로운 회원 등록
     *
     * @param user
     * @return
     */
    public User save(final User user) {
        if (user.getId() == null) {
            user.setId(getNextUserId());
        }

        userList.add(user);

        return user;
    }
}
