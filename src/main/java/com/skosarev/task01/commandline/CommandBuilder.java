package com.skosarev.task01.commandline;

import com.skosarev.task01.commandline.parser.CommandParser;
import com.skosarev.task01.commandline.parser.SimpleCommandParser;
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

    public void solve(String command) {
        CommandParser commandParser = new SimpleCommandParser(command);
        String[] args = commandParser.getArgs();

        switch (commandParser.getCommand()) {
            case "average-mark": {
                solveTask1(args);
                break;
            }
            case "excellent-students-older-than": {
                solveTask2(args);
                break;
            }
            case "lastname-search": {
                solveTask3(args);
                break;
            }
            case "quit": {
                System.out.println("Goodbye!");
                break;
            }
            default: {
                System.out.println("Unsupported operation.");
            }
        }
    }

    private void solveTask1(String[] args) {
        DataGroup gradeDataGroup = new DataGroup(new GradeCriteria());
        studentService.loadData(gradeDataGroup);

        Command solver = new AverageMarkCommand(gradeDataGroup,
                Arrays.stream(args).mapToInt(Integer::parseInt).toArray()
        );
        solver.execute();
    }

    private void solveTask2(String[] args) {
        DataGroup ageDataGroup = new DataGroup(new AgeCriteria());
        studentService.loadData(ageDataGroup);

        Command solver = new ExcellentStudentsOlderThanCommand(ageDataGroup, Integer.parseInt(args[0]));
        solver.execute();
    }

    private void solveTask3(String[] args) {
        DataGroup lastnameDataGroup = new DataGroup(new CyrillicFirstLetterCriteria());
        studentService.loadData(lastnameDataGroup);

        Command solver = new LastnameSearchCommand(lastnameDataGroup, args[0]);
        solver.execute();
    }
}
