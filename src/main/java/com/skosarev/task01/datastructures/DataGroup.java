package com.skosarev.task01.datastructures;

import com.skosarev.task01.model.Person;
import com.skosarev.task01.datastructures.criteria.GroupCriteria;

import java.util.Arrays;
import java.util.Objects;

public class DataGroup {
    private static final int START_BUCKETS_COUNT = 10;
    private static final int START_BUCKET_LENGTH = 10;

    private final GroupCriteria groupCriteria;
    private Person[][] buckets;
    private int[] nextIndexes;

    public DataGroup(GroupCriteria groupCriteria) {
        this.groupCriteria = groupCriteria;
        this.buckets = new Person[START_BUCKETS_COUNT][START_BUCKET_LENGTH];
        this.nextIndexes = new int[START_BUCKETS_COUNT];
    }

    public void addPerson(Person person) {
        int criteria = groupCriteria.criteria(person);

        // проверка на существование нужной корзины
        while (buckets.length <= criteria) {
            increaseBucketsNumber();
        }

        // проверка на переполнение нужной корзины
        if (buckets[criteria].length <= nextIndexes[criteria]) {
            increaseBucket(criteria);
        }

        buckets[criteria][nextIndexes[criteria]++] = person;
    }

    public Person[] getAllPeople() {
        // todo implement this method
        throw new UnsupportedOperationException();
    }

    public Person[] getPeopleByCriteria(Person person) {
        return Arrays.stream(buckets[groupCriteria.criteria(person)])
                .filter(Objects::nonNull)
                .toArray(Person[]::new);
    }

    // todo наверное, плохо предоставлять пользователю доступ напрямую по корзинам?
    public Person[] getPeopleByBucket(int bucketNumber) {
        return Arrays.stream(buckets[bucketNumber])
                .filter(Objects::nonNull)
                .toArray(Person[]::new);
    }

    private void increaseBucketsNumber() {
        buckets = Arrays.copyOf(buckets, buckets.length * 2);
        for (int i = buckets.length / 2; i < buckets.length; i++) {
            buckets[i] = new Person[START_BUCKET_LENGTH];
        }
        nextIndexes = Arrays.copyOf(nextIndexes, nextIndexes.length * 2);
    }

    private void increaseBucket(int bucketIndex) {
        buckets[bucketIndex] = Arrays.copyOf(buckets[bucketIndex], buckets[bucketIndex].length * 2);
    }
}
