package com.example.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 테스트용 RestController
 */
@RestController
public class HelloWorldController {
    /**
     * method : GET
     * endpoint : /hello-world
     *
     * @return String
     */
//    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    /**
     * method : GET
     * endpoint : /hello-world
     *
     * @return HelloWorldBean
     */
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }
}
