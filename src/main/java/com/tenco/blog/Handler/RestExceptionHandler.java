package com.tenco.blog.Handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tenco.blog.dto.ApiErrorResponse;
import com.tenco.blog.dto.ExceptionFieldMessage;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public void exception(Exception e) {
		System.out.println("---------");
		System.out.println(e.getClass().getName());
		System.out.println("---------");
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String illegalArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
	
//	@ExceptionHandler(value = MethodArgumentNotValidException.class)
//	public List<ExceptionFieldMessage> methodArgumentNotValidException(MethodArgumentNotValidException e) {
//	
//		List<ExceptionFieldMessage> errorList = new ArrayList<>();
//		
//		e.getBindingResult().getAllErrors().forEach( action -> {
//			FieldError fieldError = (FieldError) action; // 다운 캐스팅 
//			String fieldName = fieldError.getField();
//			String fieldMessage = fieldError.getDefaultMessage();
//			ExceptionFieldMessage exceptionFieldMessage = ExceptionFieldMessage.builder().field(fieldName).Message(fieldMessage).build();
//			errorList.add(exceptionFieldMessage);
//		});
//		
//		return errorList;
//	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ApiErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		List<ExceptionFieldMessage> errorList = new ArrayList<>();
		
		e.getBindingResult().getAllErrors().forEach( action -> {
			FieldError fieldError = (FieldError) action; // 다운 캐스팅 
			String fieldName = fieldError.getField();
			String fieldMessage = fieldError.getDefaultMessage();
			ExceptionFieldMessage exceptionFieldMessage = ExceptionFieldMessage.builder().field(fieldName).Message(fieldMessage).build();
			errorList.add(exceptionFieldMessage);
		});
		
		return ApiErrorResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value()).code("-1").resultCode("fail").message("잘못된 요청입니다.").exceptionFieldMessages(errorList).build();
	}
	
}
