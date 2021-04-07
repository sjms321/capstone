package com.example.restfulwebservice.exception;

import com.example.restfulwebservice.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice //모든 컨트롤러가 실행될때 이 어드바이스 어노테이션이 실행됨
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) //이 메소드가 ExceptionHandler 역할을 할 수 있다는 의미
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        //에러객체,어디서발생한 에러인지 알기위해request를 파라미터값으로 받는다

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
                //언제, 메세지 객체, request가 어떤 내용을 가지고있을지에 대한 설명

        return  new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                    //500번에러니까 인터널 서버로 지정 서버에서 발생하는 가장 일반적인 에러
    }

    @ExceptionHandler(UserNotFoundException.class) //이 메소드가 ExceptionHandler 역할을 할 수 있다는 의미
    public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request){
        //에러객체,어디서발생한 에러인지 알기위해request를 파라미터값으로 받는다

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        //언제, 메세지 객체, request가 어떤 내용을 가지고있을지에 대한 설명

        return  new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
        //500번에러니까 인터널 서버로 지정 서버에서 발생하는 가장 일반적인 에러
    }

    @Override//안달아도 되지만 달아주는것이 좋다
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                "Validation Failed",ex.getBindingResult().toString());

        return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
        //유효성 검사에 실패했을때의 메세지 출력을 override를 통해 재정의 해줬다.
    }

}
