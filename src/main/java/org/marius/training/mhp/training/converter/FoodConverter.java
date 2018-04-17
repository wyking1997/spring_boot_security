package org.marius.training.mhp.training.converter;

import org.marius.training.mhp.training.controller.FoodController;
import org.marius.training.mhp.training.domain.Food;
import org.marius.training.mhp.training.dto.FoodDto;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class FoodConverter extends BaseConverter<Food, FoodDto> {
    @Override
    public Food toModel(FoodDto dto) {
        return Food.builder()
                .id(dto.getId())
                .name(dto.getName())
                .ingredients(dto.getIngredients())
                .build();
    }

    @Override
    public FoodDto toDto(Food model) {
        return FoodDto.builder()
                .id(model.getId())
                .name(model.getName())
                .ingredients(model.getIngredients())
                .links(getLinkList(model))
                .build();
    }

    private List<Link> getLinkList(Food model){
        return Arrays.asList(
                linkTo(methodOn(FoodController.class).findById(model.getId())).withRel("self"),
                linkTo(methodOn(FoodController.class).increaseRating(model.getId())).withRel("increaseRatind"),
                linkTo(methodOn(FoodController.class).decreaseRating(model.getId())).withRel("decreaseRatind")
        );
    }
}
