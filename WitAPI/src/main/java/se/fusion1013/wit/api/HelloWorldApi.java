package se.fusion1013.wit.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldApi {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

}
