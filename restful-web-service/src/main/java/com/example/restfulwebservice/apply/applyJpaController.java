package com.example.restfulwebservice.apply;

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
public class applyJpaController {

    @Autowired
    private applyRepository applyRepository;

    // http://localhost:8088/jpa/apply
    @GetMapping("/apply")
    //모든 유저 보여주기
    public List<apply> retrieveAllapply(){ return applyRepository.findAll(); }

    @GetMapping("/apply/{id}")
    //특정 유저 정보 불러오기
    public apply retrieveapply(@PathVariable int id){
        Optional<apply> servletList = applyRepository.findById(id);

        return servletList.get();
    }
    @DeleteMapping("/apply/{id}")
    //유저 삭제
    public void deleteapply(@PathVariable Integer id){
        applyRepository.deleteById(id);

    }

    @PostMapping("apply")
    //유저 추가
    public ResponseEntity<apply> createList(@Valid @RequestBody apply apply){
        apply savedList = applyRepository.save(apply);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedList.getApply_id())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
