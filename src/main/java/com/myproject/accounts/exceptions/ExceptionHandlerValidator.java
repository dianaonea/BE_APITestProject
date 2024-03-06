package com.myproject.accounts.exceptions;

import com.mongodb.MongoWriteException;
import com.myproject.accounts.model.exception.GeneralErrorModel;
import jakarta.mail.MessagingException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerValidator {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public GeneralErrorModel handleValidationException (ConstraintViolationException ex){
        GeneralErrorModel errorResponse = new GeneralErrorModel();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        List<String> errorList = new ArrayList<String>();
        for (ConstraintViolation errorMessage : ex.getConstraintViolations()) {
            errorList.add(errorMessage.getMessage());
        }
        errorResponse.setMessages(errorList);
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MongoWriteException.class)
    @ResponseBody
    public GeneralErrorModel handleWriteException (MongoWriteException ex){
        GeneralErrorModel errorResponse = new GeneralErrorModel();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        List<String> errorList = new ArrayList<String>();
        errorList.add(ex.getMessage());
        errorResponse.setMessages(errorList);
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MessagingException.class)
    @ResponseBody
    public GeneralErrorModel handleEmailException (MessagingException ex){
        GeneralErrorModel errorResponse = new GeneralErrorModel();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        List<String> errorList = new ArrayList<String>();
        errorList.add(ex.getMessage());
        errorResponse.setMessages(errorList);
        return errorResponse;
    }
}
