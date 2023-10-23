package com.skosarev.task01.datastructures;

import com.skosarev.task01.Person;

import java.util.Arrays;

public class PersonNameDataGroups extends DataGroups {
    private static final int GROUPS_COUNT = 33;

    public PersonNameDataGroups() {
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

    public int getPersonHash(Person person) {
        return person.surname().charAt(0) - 1040;
    }

    public Person[] getPersons(char letter) {
        return buckets[letter - 1040];
    }

    public Person[] getBySurname(String surname) {
        Person[] result = new Person[0];

        for (Person person : getPersons(surname.charAt(0))) {
            if (person.surname().equals(surname)) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = person;
            }
        }

        return result;
    }
}