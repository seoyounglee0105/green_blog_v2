package com.tenco.blog.dto;

import java.util.List;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorResponse {

	private int statusCode;
	private String code; 
	private String message;
	private String resultCode;
	private List<ExceptionFieldMessage> exceptionFieldMessages;
	
}
