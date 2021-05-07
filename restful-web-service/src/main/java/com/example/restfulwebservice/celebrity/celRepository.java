package com.example.restfulwebservice.celebrity;

import com.example.restfulwebservice.apply.apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //데이터 베이스에 관련된 bean임을 알림
public interface celRepository extends JpaRepository<celebrity,Integer/*ID가 INTEGER니까*/> {

}
