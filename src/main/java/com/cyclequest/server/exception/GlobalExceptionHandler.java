package com.cyclequest.server.exception;

import com.cyclequest.server.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import com.cyclequest.server.constant.ResultCode;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Void> handleBusinessException(BusinessException ex) {
        log.warn("Business exception occurred: code={}, message={}", 
                ex.getResultCode().getCode(), 
                ex.getMessage());
        return ApiResponse.error(ex.getResultCode(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        String field = ex.getBindingResult().getFieldErrors().get(0).getField();
        log.warn("Validation exception: field={}, message={}", field, message);
        return ApiResponse.error(ResultCode.PARAM_ERROR, message);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        log.error("Error details: type={}, message={}", 
                ex.getClass().getName(), 
                ex.getMessage());
        return ApiResponse.error(ResultCode.INTERNAL_ERROR);
    }
}
