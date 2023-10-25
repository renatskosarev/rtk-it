package com.skosarev.task01.datastructures.criteria;

import com.skosarev.task01.model.Person;

public class CyrillicFirstLetterCriteria implements GroupCriteria {
    @Override
    public int criteria(Person person) {
        // 1040 — числовое значение кириллической 'А'
        return person.surname().charAt(0) - 1040;
    }
}
