package com.tenco.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenco.blog.model.User;
    							//<T, ID> -> T : Table의 약자 / ID : 해당 Table PK의 타입
@Repository // JpaRepository가 자동으로 처리해주기 때문에, @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer>{
	// select, selectAll, insert, update, delete 등
	

}
