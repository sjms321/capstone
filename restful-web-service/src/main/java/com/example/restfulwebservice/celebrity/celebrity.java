package com.example.restfulwebservice.celebrity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password"}) //json파일 형태에 안보이게 설정
//@JsonFilter("UserInfo")
@NoArgsConstructor
@ApiModel(description = "사용자 상세정보를 위한 도메인 객체")
@Entity//행당 클래스로 테이블 생성 컬럼으로 사용함 자동으로 db에 생성
@Table(name = "celebrity")
public class celebrity {

    @Id//기본키
    @GeneratedValue//자동생성 키값
    private int num;

    private String Cel_id;

}
