package com.skosarev.task01.datastructures;

import com.skosarev.task01.Person;

import java.util.Arrays;

public abstract class DataGroups {
    protected Person[][] buckets;
    protected int[] nextIndexes;

    public abstract void addPerson(Person person);

    protected abstract int getPersonHash(Person person);

    protected void increaseBucket(int bucketIndex) {
        Person[] oldBucket = buckets[bucketIndex];
        buckets[bucketIndex] = Arrays.copyOf(oldBucket, oldBucket.length + 1);
    }
}
