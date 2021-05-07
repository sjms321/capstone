package com.example.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service){//의존성 주입 완료
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllusers(){
        return service.findAll();
    }
    //GET / users/1 or /users/10

    @GetMapping("/users/{num}")
    public User retrieveUsers(@PathVariable int num){
        User user = service.findOne(num);
        if(user == null){
            throw new UserNotFoundException(String.format("num[%s] not found", num));
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUsers(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()//현재 요청된 request값을 사용하겠다
                .path("/{num}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{num}")
    public void deleteUser(@PathVariable int num){
        User user = service.deleteByNum(num);

        if(user == null){
            throw new UserNotFoundException(String.format("num[%s] not found", num));
        }

    }
}
