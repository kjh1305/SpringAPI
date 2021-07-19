package com.boot.springapi.exhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 전역 ExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    /*
    400 Bad Request - 클라이언트가 유효하지 않은 요청을 보낸 경우
    401 Unauthorized - 해당 서버에 클라이언트 인증이 실패한 경우
    403 Forbidden - 클라이언트가 인증은 됐지만 요청한 자원에 대한 권한은 없는 경우
    404 Not Found - 요청한 자원이 존재하지 않는 경우
    412 Precondition Failed - Request Header 필드 중 한 개 이상의 값이 잘못 된
    */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResult notReadExHandler(Exception e){
        log.error("ex", e);
        return new ErrorResult("유효하지 않은 요청", e.getMessage());
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ErrorResult notMediaTypeNotSupport(Exception e){
        log.error("ex", e);
        return new ErrorResult("JSON 타입만 지원합니다.", e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        log.error("ex", e);
        return new ErrorResult("EX", e.getMessage());
    }
}
