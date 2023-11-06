package com.skosarev.task01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student {
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

    public Student(String firstName, String lastName, int age, String group, int... grades) {
        this(firstName, lastName, age, group, grades[0], grades[1], grades[2], grades[3], grades[4], grades[5]);
    }

    public double averageMark() {
        return (physics + mathematics + russian + literature + geometry + informatics) / 6.0;
    }

    public boolean isExcellentStudent() {
        return averageMark() == 5.0;
    }

    public String getFullName() {
        return String.format("%s %s", lastName, firstName);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", group='" + group + '\'' +
                '}';
    }
}
