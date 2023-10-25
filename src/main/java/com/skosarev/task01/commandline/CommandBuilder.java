package com.skosarev.task01.commandline;

import com.skosarev.task01.datastructures.DataGroup;
import com.skosarev.task01.datastructures.criteria.AgeCriteria;
import com.skosarev.task01.datastructures.criteria.CyrillicFirstLetterCriteria;
import com.skosarev.task01.datastructures.criteria.GradeCriteria;
import com.skosarev.task01.service.StudentService;

import java.util.Arrays;

// Не уверен, что правильно организовал работу с консольными командами.
public class CommandBuilder {
    private final StudentService studentService;

    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
    }

    public void solve(String action, String argument) {
        switch (action) {
            case "average-mark": {
                solveTask1(argument);
                break;
            }
            case "excellent-students-older-than": {
                solveTask2(argument);
                break;
            }
            case "lastname-search": {
                solveTask3(argument);
                break;
            }
            default: {
                System.out.println("Unsupported operation.");
            }
        }
    }

    private void solveTask1(String argument) {
        DataGroup gradeDataGroup = new DataGroup(new GradeCriteria());
        studentService.loadData(gradeDataGroup);

        Command solver = new AverageMarkCommand(gradeDataGroup,
                Arrays.stream(argument.split(" ")).mapToInt(Integer::parseInt).toArray()
        );
        solver.execute();
    }

    private void solveTask2(String argument) {
        DataGroup ageDataGroup = new DataGroup(new AgeCriteria());
        studentService.loadData(ageDataGroup);

        Command solver = new ExcellentStudentsOlderThanCommand(ageDataGroup, Integer.parseInt(argument));
        solver.execute();
    }

    private void solveTask3(String lastname) {
        DataGroup lastnameDataGroup = new DataGroup(new CyrillicFirstLetterCriteria());
        studentService.loadData(lastnameDataGroup);

        Command solver = new LastnameSearchCommand(lastnameDataGroup, lastname);
        solver.execute();
    }
}
