package com.skosarev.task01.datastructures.criteria;

import com.skosarev.task01.model.Person;

@FunctionalInterface
public interface GroupCriteria {
    int criteria(Person person);
}
