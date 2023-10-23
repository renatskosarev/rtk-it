package com.skosarev.task01.datastructures;

import com.skosarev.task01.Person;

import java.util.Arrays;
import java.util.Objects;


public class ClassroomDataGroups extends DataGroups {
    private static final int GROUPS_COUNT = 12;

    public ClassroomDataGroups() {
        buckets = new Person[GROUPS_COUNT][0];
        nextIndexes = new int[GROUPS_COUNT];
    }

    public void addPerson(Person person) {
        int personHash = getPersonHash(person);

        // проверка заполненности bucket'а
        if (buckets[personHash].length == nextIndexes[personHash]) {
            increaseBucket(personHash);
        }

        buckets[personHash][nextIndexes[personHash]++] = person;
    }

    public Person[] getPersons(int groupNum) {
        return Arrays.stream((buckets[groupNum - 1])).filter(Objects::nonNull).toArray(Person[]::new);
    }

    public int getPersonHash(Person person) {
        return person.group() - 1;
    }
}