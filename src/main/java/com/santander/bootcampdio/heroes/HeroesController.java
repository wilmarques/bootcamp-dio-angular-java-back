package com.santander.bootcampdio.heroes;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HeroesController {

  private final HeroRepository repository;

  HeroesController(HeroRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/heroes")
  CollectionModel<EntityModel<Hero>> all() {

    List<EntityModel<Hero>> heroes = repository.findAll().stream()
        .map(hero -> EntityModel.of(hero,
            linkTo(methodOn(HeroesController.class).one(hero.getId())).withSelfRel(),
            linkTo(methodOn(HeroesController.class).all()).withRel("heroes")))
        .collect(Collectors.toList());

    return CollectionModel.of(heroes, linkTo(methodOn(HeroesController.class).all()).withSelfRel());
  }

  @PostMapping("/heroes")
  Hero newHero(@RequestBody Hero newHero) {
    return repository.save(newHero);
  }

  @GetMapping("/heroes/{id}")
  EntityModel<Hero> one(@PathVariable Long id) {

    Hero heroes = repository.findById(id) //
        .orElseThrow(() -> new HeroNotFoundException(id));

    return EntityModel.of(heroes, //
        linkTo(methodOn(HeroesController.class).one(id)).withSelfRel(),
        linkTo(methodOn(HeroesController.class).all()).withRel("heroes"));
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
