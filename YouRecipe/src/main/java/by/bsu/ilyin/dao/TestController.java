package by.bsu.ilyin.dao;

import by.bsu.ilyin.entities.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/")
    public String helloWorld(){
        return "Hello world!";
    }
    @PostMapping("/hello")
    public String hello(@RequestBody String name){
        return "Hello " + name;
    }
    @GetMapping("user")
    public User getUser(){
        return new User();
    }
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public User helloUser(@PathVariable String name, @RequestParam(value = "v", required = false)Integer age){
return null;
    }
}
