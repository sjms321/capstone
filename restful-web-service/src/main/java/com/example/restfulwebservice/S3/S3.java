package com.example.restfulwebservice.S3;

import com.example.restfulwebservice.apply.apply;
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
@Table(name = "S3")
public class S3 {
    @Id//기본키
    @GeneratedValue//자동생성 키값
    private int id;

    private String url;
    private String code;

    @ManyToOne
    @JoinColumn(name = "Apply_id")
    private apply apply_id;

}
