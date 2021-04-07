package com.example.restfulwebservice.helloworld;
//lombok: 세터 게터 자동 생성 -> 개발시간 단축

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//모든 필드의 속성값들에대한 게터 세터 만들어짐
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldBean {
    private String message;

}

