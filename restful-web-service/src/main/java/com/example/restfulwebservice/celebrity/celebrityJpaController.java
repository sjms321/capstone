package com.example.restfulwebservice.celebrity;

import com.example.restfulwebservice.apply.apply;
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
public class celebrityJpaController {

    @Autowired
    private com.example.restfulwebservice.celebrity.celRepository celRepository;

    // http://localhost:8088/jpa/cel
    @GetMapping("/cel")
    //모든 유저 보여주기
    public List<celebrity> retrieveAllcel(){ return celRepository.findAll(); }

    @GetMapping("/cel/{id}")
    //특정 유저 정보 불러오기
    public celebrity retrievecel(@PathVariable int id){
        Optional<celebrity> servletList = celRepository.findById(id);

        return servletList.get();
    }

    @DeleteMapping("/cel/{id}")
    //유저 삭제
    public void deletecel(@PathVariable Integer id){
        celRepository.deleteById(id);

    }

    @PostMapping("cel")
    //유저 추가
    public ResponseEntity<celebrity> createList(@Valid @RequestBody celebrity celebrity){
        celebrity savedList = celRepository.save(celebrity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedList.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
