package org.marius.training.mhp.training.converter;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class BaseConverter<M,D> {

    public abstract M toModel(D dto);

    public abstract D toDto(M model);

    public List<M> toModels(Collection<D> dtos){
        return dtos
                .stream()
                .map(dto -> toModel(dto))
                .collect(Collectors.toList());
    }

    public List<D> toDtos(Collection<M> models){
        return models
                .stream()
                .map(model -> toDto(model))
                .collect(Collectors.toList());
    }
}
