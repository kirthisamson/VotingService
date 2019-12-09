package com.kirthisamson.votingsystem.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionResponse {
  private HttpStatus httpstatus;
  private String errorMessage;
  private LocalDateTime time;
}
