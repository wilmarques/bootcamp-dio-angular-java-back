package com.santander.bootcampdio.heroes;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class HeroModelAssembler implements RepresentationModelAssembler<Hero, EntityModel<Hero>> {

  @Override
  public EntityModel<Hero> toModel(Hero hero) {

    return EntityModel.of(hero, //
        linkTo(methodOn(HeroesController.class).one(hero.getId())).withSelfRel(),
        linkTo(methodOn(HeroesController.class).all()).withRel("heroes"));
  }
}
