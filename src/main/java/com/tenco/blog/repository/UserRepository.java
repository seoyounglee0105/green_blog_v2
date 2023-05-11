package com.tenco.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tenco.blog.model.User;
    							//<T, ID> -> T : Table의 약자 / ID : 해당 Table PK의 타입
@Repository // JpaRepository가 자동으로 처리해주기 때문에, @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer>{
	// select, selectAll, insert, update, delete 등
	
	// Spring JPA 네이밍 전략
	// 메서드 이름으로 JPA 쿼리를 만들어줌
	// SELECT * FROM user WHERE username = ? AND password = ?
	User findByUsernameAndPassword(String username, String password);
	
	// 두 번째 방법
	// 네이티브 쿼리 작성
	@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
	User login(String username, String password);
 
}
