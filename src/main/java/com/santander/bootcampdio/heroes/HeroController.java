package com.santander.bootcampdio.heroes;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HeroController {

  private final HeroRepository repository;

  HeroController(HeroRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/heroes")
  List<Hero> all() {
    return repository.findAll();
  }

  @PostMapping("/heroes")
  Hero newHero(@RequestBody Hero newHero) {
    return repository.save(newHero);
  }
  
  @GetMapping("/heroes/{id}")
  Hero one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new HeroNotFoundException(id));
  }

  @PutMapping("/heroes/{id}")
  Hero replaceHero(@RequestBody Hero newHero, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(employee -> {
        employee.setName(newHero.getName());
        employee.setDescription(newHero.getDescription());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newHero.setId(id);
        return repository.save(newHero);
      });
  }

  @DeleteMapping("/heroes/{id}")
  void deleteHero(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
