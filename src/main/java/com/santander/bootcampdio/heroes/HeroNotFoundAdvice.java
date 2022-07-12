package com.santander.bootcampdio.heroes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HeroNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(HeroNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String heroNotFoundHandler(HeroNotFoundException ex) {
    return ex.getMessage();
  }
}
