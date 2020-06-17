package com.example.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * 테스트용 RestController
 */
@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;

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

    /**
     * method : GET
     * endpoint : /hello-world-internationalized
     *
     * @return
     */
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) final Locale locale) {
        return this.messageSource.getMessage("greeting.message", null, locale);
    }

}
