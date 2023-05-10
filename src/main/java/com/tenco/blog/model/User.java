package com.tenco.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;



// 자동으로 해당 멤버변수들을 컬럼으로 갖는 CREATE문을 실행해줌
@Entity // 도메인 객체라고도 부름 : 테이블 역할 // DTO 역할도 함
@Data // 주의 : (Object Mapper가) 파싱 처리할 때 setter 메서드가 반드시 있어야 함
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 설정
	private int id; 
	          // NOT NULL      // 길이 제한
	@Column(nullable = false, length = 30)
	private String username;
	
	@NotBlank // null, "" 불가능
	@Column(nullable = false, length = 100)
	private String password;
	
	@NotBlank // null, "" 불가능
	@Column(nullable = false, length = 50)
	private String email;
	
	// 문자열은 반드시 홑따옴표와 함께
	// 숫자는 그냥 써
	@ColumnDefault("'user'") // 기본값 설정 (Default)
	private String role; // 도메인 : 데이터의 범주화 (user, admin, manager)
	
	@CreationTimestamp // now()로 자동 입력 
	private Timestamp createdDate;
	
}
