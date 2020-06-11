package com.example.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * endpoint : /hello-world-bean
     *
     * @return HelloWorldBean
     */
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    /**
     * method : GET
     * endpoint : /hello-world-bean/path-variable/{name}
     *
     * @param name String
     * @return HelloWorldBean
     */
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable final String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
