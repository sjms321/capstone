package com.example.restfulwebservice.apply;


import com.example.restfulwebservice.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity//행당 클래스로 테이블 생성 컬럼으로 사용함 자동으로 db에 생성
public class Apply {
    @Id//기본키
    @GeneratedValue//자동생성 키값
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
    private Integer celid;
    private String URL;
    private String title;
    private String contents;
    private String email;
}
