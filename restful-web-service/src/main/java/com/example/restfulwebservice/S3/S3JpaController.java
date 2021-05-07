package com.example.restfulwebservice.S3;


import com.example.restfulwebservice.user.User;
import com.example.restfulwebservice.user.UserNotFoundException;
import com.example.restfulwebservice.user.UserRepository;
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
public class S3JpaController {

    @Autowired
    private S3Repository S3Repository;

    // http://localhost:8088/jpa/users
    @GetMapping("/S3")
    //모든 유저 보여주기
    public List<S3> retrieveAllS3(){
        return S3Repository.findAll();
    }

    @GetMapping("/S3/{id}")
    //특정 유저 정보 불러오기
    public S3 retrieveS3(@PathVariable int id){
        Optional<S3> servletS3 = S3Repository.findById(id);

        return servletS3.get();
    }
    @DeleteMapping("/S3/{id}")
    //유저 삭제
    public void deleteS3(@PathVariable Integer id){
        S3Repository.deleteById(id);

    }

    @PostMapping("/S3")
    //유저 추가
    public ResponseEntity<S3> createS3(@Valid @RequestBody S3 S3){
        S3 savedS3 = S3Repository.save(S3);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedS3.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
