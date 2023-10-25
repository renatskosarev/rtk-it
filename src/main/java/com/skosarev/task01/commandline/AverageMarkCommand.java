package com.skosarev.task01.commandline;

import com.skosarev.task01.datastructures.DataGroup;
import com.skosarev.task01.model.Person;

public class AverageMarkCommand implements Command {
    private final DataGroup dataGroup;
    private final int[] grades;

    public AverageMarkCommand(DataGroup dataGroup, int[] grades) {
        this.dataGroup = dataGroup;
        this.grades = grades;
    }

    @Override
    public void execute() {
        double sum = 0;
        int count = 0;

        for (int grade : grades) {
            try {
                for (Person person : this.dataGroup.getPeopleByBucket(grade)) {
                    sum += person.averageMark();
                    count += 1;
                }
            } catch (RuntimeException e) {
                System.err.println("Invalid grade: " + grade);
                return;
            }
        }

        if (count == 0) {
            System.out.println("No students");
        } else {
            System.out.println("Average mark: " + sum / count);
        }
    }
}
