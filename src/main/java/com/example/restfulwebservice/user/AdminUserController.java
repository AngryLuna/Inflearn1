package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 회원 정보 처리 RestController
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private final UserDaoService service;

    /**
     * 생성자
     *
     * @param service
     */
    public AdminUserController(final UserDaoService service) {
        this.service = service;
    }

    /**
     * 전체 회원 정보 검색
     *
     * @return
     */
    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        return filterValue(this.service.findAll(), "UserInfo", "id", "name", "joinDate", "ssn");
    }

    /**
     * 회원 ID로 회원 정보 검색 v1
     *
     * @param id
     * @return
     */
//    @GetMapping("/v1/users/{id}")
    @GetMapping(value = "/users/{id}", params = "version=1")
    public MappingJacksonValue retrieveUserV1(@PathVariable final int id) {
        final User user = this.service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }

        return filterValue(user, "UserInfo", "id", "name", "joinDate", "ssn");
    }

    /**
     * 회원 ID로 회원 정보 검색 v2
     *
     * @param id
     * @return
     */
//    @GetMapping("/v2/users/{id}")
    @GetMapping(value = "/users/{id}", params = "version=2")
    public MappingJacksonValue retrieveUserV2(@PathVariable final int id) {
        final User user = this.service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }

        final UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP"); // 임시로 VIP 등급으로 지정

        return filterValue(userV2, "UserInfoV2", "id", "name", "joinDate", "grade");
    }

    /**
     * 회원 정보 필터링 처리
     *
     * @param value
     * @param filterName
     * @param fields
     * @return
     */
    private static MappingJacksonValue filterValue(final Object value, final String filterName, final String... fields) {
        final SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);

        final FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter(filterName, filter);

        final MappingJacksonValue mapping = new MappingJacksonValue(value);
        mapping.setFilters(filterProvider);

        return mapping;
    }
}
