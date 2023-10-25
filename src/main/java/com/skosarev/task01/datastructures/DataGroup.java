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
        Person[] result = new Person[0];
        for (Person[] bucket : buckets) {
            result = concatWithArrayCopy(result, bucket);
        }

        return filterNotNull(result);
    }

    public Person[] getPeopleByCriteria(Person person) {
        return filterNotNull(buckets[groupCriteria.criteria(person)]);
    }

    public Person[] getPeopleByBucket(int bucketNumber) {
        if (bucketNumber < 0 || bucketNumber >= buckets.length) {
            throw new RuntimeException("Bucket with number " + bucketNumber + " not exists");
        }
        return filterNotNull(buckets[bucketNumber]);
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

    private static <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }

    private static Person[] filterNotNull(Person[] people) {
        return Arrays.stream(people).filter(Objects::nonNull).toArray(Person[]::new);
    }
}
