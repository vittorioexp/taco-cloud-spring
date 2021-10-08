package com.example.tacos.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

// Lombok automatically generates equals(), hashCode(), toString(), ... at runtime

/*
the @Data annotation at the class level is provided by Lombok and tells
Lombok to generate all of those missing methods as well as a constructor that accepts
all final properties as arguments.
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@ToString
@Entity
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final Type type;
    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
