package com.skosarev.task01.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
}
