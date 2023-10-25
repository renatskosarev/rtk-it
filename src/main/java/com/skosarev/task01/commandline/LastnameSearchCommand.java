package com.skosarev.task01.commandline;

import com.skosarev.task01.datastructures.DataGroup;
import com.skosarev.task01.model.Person;

import java.util.Arrays;

public class LastnameSearchCommand implements Command {
    private final DataGroup dataGroup;
    private final String surname;

    public LastnameSearchCommand(DataGroup dataGroup, String surname) {
        this.dataGroup = dataGroup;
        this.surname = surname;
    }

    @Override
    public void execute() {
        Person[] result = new Person[0];

        // Вот это прям плохо. Сильная связанность
        for (Person person : this.dataGroup.getPeopleByBucket(surname.charAt(0) - 1040)) {
            if (person.surname().equals(surname)) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = person;
            }
        }

        for (Person person : result) {
            System.out.println(person);
        }
    }
}
