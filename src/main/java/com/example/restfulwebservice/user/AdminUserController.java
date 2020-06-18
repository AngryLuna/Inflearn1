package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
        return filterValue(this.service.findAll());
    }

    /**
     * 회원 ID로 회원 정보 검색
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable final int id) {
        final User user = this.service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }

        return filterValue(user);
    }

    /**
     * 회원 정보 필터링 처리
     *
     * @param value
     * @return
     */
    private static MappingJacksonValue filterValue(final Object value) {
        final SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");

        final FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("UserInfo", filter);

        final MappingJacksonValue mapping = new MappingJacksonValue(value);
        mapping.setFilters(filterProvider);

        return mapping;
    }
}
