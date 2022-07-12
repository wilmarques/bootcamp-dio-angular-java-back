package com.santander.bootcampdio.heroes;

public class HeroNotFoundException extends RuntimeException {

  HeroNotFoundException(Long id) {
    super("Hero id: " + id + " not found.");
  }
}
