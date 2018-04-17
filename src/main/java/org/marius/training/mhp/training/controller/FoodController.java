package org.marius.training.mhp.training.controller;

import org.marius.training.mhp.training.converter.FoodConverter;
import org.marius.training.mhp.training.dto.FoodDto;
import org.marius.training.mhp.training.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController extends RootController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodConverter foodConverter;

    @PostMapping
    public @ResponseBody ResponseEntity<FoodDto> createFood(@RequestBody FoodDto food){
        return new ResponseEntity<>(foodConverter.toDto(foodService.save(foodConverter.toModel(food))), HttpStatus.OK);
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<FoodDto>> getAll(){
        return new ResponseEntity<>(foodConverter.toDtos(foodService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<FoodDto> findById(@PathVariable long id){
        FoodDto a = foodConverter.toDto(foodService.findById(id));
        return new ResponseEntity<FoodDto>(a, HttpStatus.OK);
    }

    @PutMapping("/update")
    public @ResponseBody ResponseEntity<FoodDto> update(@RequestBody FoodDto foodDto){
        return ResponseEntity.status(HttpStatus.OK).body(foodConverter.toDto(
                foodService.update(foodConverter.toModel(foodDto)))
        );
    }

    @PutMapping("increase/{foodId}")
    public ResponseEntity<Long> increaseRating(@PathVariable Long foodId){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.increaseRating(foodId));
    }

    @PutMapping("decrease/{foodId}")
    public ResponseEntity<Long> decreaseRating(@PathVariable Long foodId){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.decreaseRating(foodId));
    }

    @DeleteMapping("delete/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable long id){
        foodService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
