package com.example.restfulwebservice.apply;

import com.example.restfulwebservice.celebrity.celebrity;
import com.example.restfulwebservice.user.User;
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
@Table(name = "apply")
public class apply {
    @Id//기본키
    @GeneratedValue//자동생성 키값
    private Integer apply_id; // apply 기본키

    private String name; // 신청한 연예인 이름
    private String email;  // 발송할 이메일
    private String title;  // 신청 사연 제목
    private String request; // 신청자 요청사항
    private String story; // 신청자 사연
    private String selectedYoutuberName; // 선택한 연예인
    private String code; // 동영상 코드
    //private String celId; // ??
    private String userId; // 신청자 아이디
}
