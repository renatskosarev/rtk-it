package com.skosarev.task01.model.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private String firstName;
    private String lastName;
    private int age;
    private String group;

    private int mathematics;
    private int geometry;
    private int informatics;
    private int physics;
    private int russian;
    private int literature;

    public StudentDTO(String firstName, String lastName, int age, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.group = group;
    }
}
