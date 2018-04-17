package org.marius.training.mhp.training.service;

import org.marius.training.mhp.training.domain.Food;
import org.marius.training.mhp.training.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class FoodServiceImpl implements FoodService {

    private Map<Long, Food> ls= new HashMap<>();
    {
        ls.put(0l, new Food(0, "salad", Arrays.asList("cucumber","carot"), 1));
        ls.put(1l, new Food(1, "salad1", Arrays.asList("cucumber1","carot1"), 1));
        ls.put(2l, new Food(2, "salad2", Arrays.asList("cucumber2","carot2"), 1));
    }
    private AtomicInteger atomicInteger = new AtomicInteger(2);

    @Override
    public Food findById(long id) {
        try {
            return ls.get(id);
        } catch (Exception e){
            throw  new CustomException("not ok");
        }
    }

    @Override
    public Collection<Food> findAll() {
        return ls.values();
    }

    @Override
    public Food save(Food food) {
        food.setId(atomicInteger.incrementAndGet());
        ls.put(food.getId(), food);
        return food;
    }

    @Override
    public Food update(Food food) {
        if (!ls.containsKey(food.getId()))
            throw  new CustomException("not ok");
        ls.put(food.getId(), food);
        return food;
    }

    @Override
    public void delete(long id) {
        if (!ls.containsKey(id))
            throw  new CustomException("not ok");
        ls.remove(id);
    }

    @Override
    public long increaseRating(long foodId) {
        if (!ls.containsKey(foodId))
            throw  new CustomException("not ok");
        ls.get(foodId).setRating(ls.get(foodId).getRating() + 1);
        return ls.get(foodId).getRating();
    }

    @Override
    public long decreaseRating(long foodId) {
        if (!ls.containsKey(foodId))
            throw  new CustomException("not ok");
        ls.get(foodId).setRating(ls.get(foodId).getRating() - 1);
        return ls.get(foodId).getRating();
    }


}
