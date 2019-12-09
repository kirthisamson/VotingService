package com.kirthisamson.votingsystem.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NonUniqueResultException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class VotingServiceErrorAdvice {

  @ExceptionHandler({UserNotFoundException.class})
  public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
    return error(HttpStatus.NOT_FOUND, e);
  }

  @ExceptionHandler({NoSuchElementException.class})
  public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e){
    return error(HttpStatus.NOT_FOUND, e);
  }

  @ExceptionHandler({UserAlreadyExistsException.class})
  public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e){
    return error(HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler({NonUniqueResultException.class})
  public ResponseEntity<Object> handleUserAlreadyExistsException(NonUniqueResultException e){
    return error(HttpStatus.BAD_REQUEST, e);
  }

  private ResponseEntity<Object> error(HttpStatus status, Exception e) {
    log.error("Exception : ", e);
    ExceptionResponse er = ExceptionResponse.builder()
                                            .errorMessage(e.getMessage())
                                            .httpstatus(status)
                                            .time(LocalDateTime.now())
                                            .build();
    return buildResponseEntity(er);
  }

  private ResponseEntity<Object> buildResponseEntity(ExceptionResponse er) {
    return new ResponseEntity<>(er, er.getHttpstatus());
  }
}
