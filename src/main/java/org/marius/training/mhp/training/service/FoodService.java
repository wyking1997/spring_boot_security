package org.marius.training.mhp.training.service;


import org.marius.training.mhp.training.domain.Food;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface FoodService {
    Food findById(long id);

    Collection<Food> findAll();

    Food save(Food food);

    Food update(Food food);

    void delete(long id);

    long increaseRating(long foodId);

    long decreaseRating(long foodId);
}
