package com.example.restfulwebservice.apply;


import com.example.restfulwebservice.user.User;
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
public class ApplyJpaController {

    @Autowired
    private ApplyRepository applyRepository;

    @GetMapping("/applys")
    //모든 신청 보여주기
    public List<Apply> retrieveAllUsers(){
        return applyRepository.findAll();
    }

    @GetMapping("/applys/{celid}")
    //셀럽의 해당하는 신청 리스트 불러오기
    public  List<Apply> retrieveApply(@PathVariable int celid){
        List<Apply> apply = applyRepository.findAllById(celid);
        return apply;
    }
    @DeleteMapping("/applys/{id}")
    //기본키로 해당 신청 삭제 동영상 업로드 혹은 기한 만료시 사용예정
    public void deleteUser(@PathVariable int id){
        applyRepository.deleteById(id);
    }

    @PostMapping("/applys")
    //유저 추가
    public ResponseEntity<Apply> createUser(@Valid @RequestBody Apply apply){
        Apply savedUser = applyRepository.save(apply);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
