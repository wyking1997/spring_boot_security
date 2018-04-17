package org.marius.training.mhp.training.controller;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {
    class Root extends ResourceSupport{}

    @RequestMapping
    public ResponseEntity<Root> getRoot(){
        final Root root = new Root();
        root.add(getLink());
        return new ResponseEntity<>(root, HttpStatus.OK);
    }

    private Link getLink() {
        final Link link = ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(FoodController.class)
                .getAll())
                .withRel("foodList");
        return link;
    }
}
