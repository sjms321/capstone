package com.example.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // GET
    // /hello-world (endpoint)
    //@RequestMapping(method = RequestMethod.GET ,path = "/hello - world")//예전방식
    @GetMapping(path= "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }
    // alt + enter
    @GetMapping(path= "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){//json으로 변환해줌
        return new HelloWorldBean("Hello World");
    }


    @GetMapping(path= "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
