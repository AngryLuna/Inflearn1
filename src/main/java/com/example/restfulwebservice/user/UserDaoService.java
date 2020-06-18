package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 회원 정보 비즈니스 로직 처리 클래스 (DAO, Service 클래스 통합)
 */
@Service
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
        userList.add(new User(getNextUserId(), "TestUser1", new Date(), "pwd1", "111111-2222222"));
        userList.add(new User(getNextUserId(), "TestUser2", new Date(), "pwd2", "333333-4444444"));
        userList.add(new User(getNextUserId(), "TestUser3", new Date(), "pwd3", "555555-6666666"));
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

    /**
     * ID로 회원 삭제
     *
     * @param id
     * @return
     */
    public User deleteById(final int id) {
        final Iterator<User> iterator = this.userList.iterator();

        while (iterator.hasNext()) {
            final User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }

    /**
     * 회원 정보 수정
     *
     * @param user
     * @return
     */
    public User update(final User user) {
        final User updateUser = findOne(user.getId());

        if (updateUser == null) {
            return null;
        }

        updateUser.update(user);

        return updateUser;
    }
}
