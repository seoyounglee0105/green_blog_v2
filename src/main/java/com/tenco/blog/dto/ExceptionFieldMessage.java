package com.tenco.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@Builder // 빌더 패턴 쓰려면 밑에 두 어노테이션도 같이
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionFieldMessage {
	
	private String field; // 에러가 발생한 필드
	private String Message; // 에러 메시지
	
}
