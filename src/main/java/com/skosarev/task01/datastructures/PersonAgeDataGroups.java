package com.skosarev.task01.datastructures;

import com.skosarev.task01.Person;

import java.util.Arrays;

public class PersonAgeDataGroups extends DataGroups {
    private static final int GROUPS_COUNT = 13;

    public PersonAgeDataGroups() {
        buckets = new Person[GROUPS_COUNT][0];
        nextIndexes = new int[GROUPS_COUNT];
    }

    public int getPersonHash(Person person) {
        return person.age() - 5;
    }

    public void addPerson(Person person) {
        int personHash = getPersonHash(person);

        // проверка заполненности bucket'а
        if (buckets[personHash].length == nextIndexes[personHash]) {
            increaseBucket(personHash);
        }

        buckets[personHash][nextIndexes[personHash]++] = person;
    }

    public Person[] getPersons(int age) {
        return this.buckets[age - 5];
    }

    public Person[] getPersonsAgeGreaterThan(int age) {
        Person[] result = new Person[0];
        for (int i = age + 1; i < 18; i++) {
            result = concatWithArrayCopy(result, getPersons(i));
        }
        return result;
    }

    static <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
}