package org.marius.training.mhp.training.domain;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Food {
    private long id;
    private String name;
    private List<String> ingredients;
    private long rating;
}
