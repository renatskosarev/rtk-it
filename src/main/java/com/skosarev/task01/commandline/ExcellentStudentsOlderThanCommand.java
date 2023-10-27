package com.skosarev.task01.commandline;

import com.skosarev.task01.datastructures.DataGroup;
import com.skosarev.task01.model.Person;

import java.util.Arrays;

public class ExcellentStudentsOlderThanCommand implements Command {
    private final DataGroup dataGroup;
    private final int startAge;

    public ExcellentStudentsOlderThanCommand(DataGroup dataGroup, int startAge) {
        this.dataGroup = dataGroup;
        this.startAge = startAge;
    }

    @Override
    public void execute() {
        System.out.println("Excellent students age " + this.startAge + "+:");
        for (Person person : getPersonsAgeGreaterThan(this.startAge)) {
            if (person.isExcellentStudent()) {
                System.out.println(person);
            }
        }
    }

    public Person[] getPersonsAgeGreaterThan(int age) {
        Person[] result = new Person[0];
        for (int i = age; i < 18; i++) {
            result = concatWithArrayCopy(result, this.dataGroup.getPeopleByBucket(i));
        }
        return result;
    }

    static <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
}
