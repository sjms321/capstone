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

    @GetMapping("/users/{id}")
    public User retrieveUsers(@PathVariable int id){
        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] nor found", id));
        }


        return user;
    }
    

    @PostMapping("/users")
    public ResponseEntity<User> createUsers(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()//현재 요청된 request값을 사용하겠다
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void delteUser(@PathVariable int id){
        User user = service.deletByID(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] nor found", id));
        }

    }
}
