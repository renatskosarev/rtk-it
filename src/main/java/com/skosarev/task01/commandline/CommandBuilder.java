package com.skosarev.task01.commandline;

import com.skosarev.task01.commandline.parser.CommandParser;
import com.skosarev.task01.commandline.parser.SimpleCommandParser;
import com.skosarev.task01.service.StudentService;

import java.sql.SQLException;


public class CommandBuilder {
    private final StudentService studentService;

    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
    }

    public void solve(String command) throws SQLException {
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
            case "average-mark-by-lastname": {
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

    private void solveTask1(String[] args) throws SQLException {
        Command solver = new AverageMarkCommand(studentService.getDAO(), args);
        solver.execute();
    }

    private void solveTask2(String[] args) throws SQLException {
        Command solver = new ExcellentStudentsOlderThanCommand(studentService.getDAO(), Integer.parseInt(args[0]));
        solver.execute();
    }

    private void solveTask3(String[] args) throws SQLException {
        Command solver = new AverageMarkByLastnameCommand(studentService.getDAO(), args[0]);
        solver.execute();
    }
}
