package com.skosarev.task01;

public record Person(String name, String surname, int age, int group,
                     int physics, int mathematics, int rus, int literature, int geometry, int informatics) {

    public Person(String name, String surname, int age, int group, int... grades) {
        this(name, surname, age, group, grades[0], grades[1], grades[2], grades[3], grades[4], grades[5]);
    }

    public double averageGrade() {
        return (physics + mathematics + rus + literature + geometry + informatics) / 6.0;
    }

    public boolean isExcellentStudent() {
        return physics == 5 && mathematics == 5 && rus == 5 && literature == 5 && geometry == 5 && informatics == 5;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", group=" + group +
                '}';
    }
}
