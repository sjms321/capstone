package com.example.restfulwebservice.apply;

import com.example.restfulwebservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository //데이터 베이스에 관련된 bean임을 알림
public interface applyRepository extends JpaRepository<apply,Integer/*ID가 INTEGER니까*/> {

    apply findByTitle(String Title);

    List<apply> findByselectedYoutuberName(String name);

}
