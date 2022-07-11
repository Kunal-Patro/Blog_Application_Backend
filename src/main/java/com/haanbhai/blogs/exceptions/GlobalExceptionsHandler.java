package com.haanbhai.blogs.exceptions;

import com.haanbhai.blogs.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        String msg = ex.getMessage();
        ApiResponse apiRes = new ApiResponse(msg,false);
        return new ResponseEntity<ApiResponse>(apiRes, HttpStatus.NOT_FOUND);
    }
}
