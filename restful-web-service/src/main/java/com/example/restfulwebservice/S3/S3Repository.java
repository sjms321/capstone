package com.example.restfulwebservice.S3;

import com.example.restfulwebservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository //데이터 베이스에 관련된 bean임을 알림
public interface S3Repository extends JpaRepository<S3,Integer/*ID가 INTEGER니까*/> {

    //@Query("select s.url from S3 s where s.code = ?1")
    //List<Object> getCode(String code);
    Optional<S3> findBycode(String code);
}