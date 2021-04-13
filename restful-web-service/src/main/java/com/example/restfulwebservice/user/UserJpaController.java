package com.example.restfulwebservice.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    @Autowired
    private UserRepository userRepository;

    // http://localhost:8088/jpa/users
    @GetMapping("/users")
    //모든 유저 보여주기
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{num}")
    //특정 유저 정보 불러오기
    public User retrieveUser(@PathVariable int num){
        Optional<User> user = userRepository.findById(num);

        if (!user.isPresent()) {

            throw new UserNotFoundException(String.format("num[%s] not found",num));
        }

        return user.get();
    }
    @DeleteMapping("/users/{num}")
    //유저 삭제
    public void deleteUser(@PathVariable Integer num){
        userRepository.deleteById(num);

    }
    @PostMapping("/users")
    //유저 추가
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{num}")
                .buildAndExpand(savedUser.getNum())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
