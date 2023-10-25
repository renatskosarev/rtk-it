package com.skosarev.task01.datastructures.criteria;

import com.skosarev.task01.model.Person;

public class AgeCriteria implements GroupCriteria {

    @Override
    public int criteria(Person person) {
        return person.age();
    }
}
