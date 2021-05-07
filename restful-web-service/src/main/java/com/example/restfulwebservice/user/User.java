package com.example.restfulwebservice.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password"}) //json파일 형태에 안보이게 설정
//@JsonFilter("UserInfo")
@NoArgsConstructor
@ApiModel(description = "사용자 상세정보를 위한 도메인 객체")
@Entity//행당 클래스로 테이블 생성 컬럼으로 사용함 자동으로 db에 생성
@Table(name = "user")
public class User {

    @Id//기본키
    @GeneratedValue//자동생성 키값
    private Integer id;

    @Column(name="User_id")
    private String user_id;


    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        user_id = user_id;
    }

    public User(int num, String User_id) {
        this.id = num;
        this.user_id = User_id;
    }

}


