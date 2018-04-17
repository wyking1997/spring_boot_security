package org.marius.training.mhp.training.dto;

import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class FoodDto {
    private long id;
    private String name;
    private List<String> ingredients;
    private int rating;

    private List<Link> links;

    public void addLink(Link link){
        if (links == null)
            links = new LinkedList<>();
        links.add(link);
    }
}
