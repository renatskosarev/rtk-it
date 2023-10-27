package com.skosarev.task01;

import com.skosarev.task01.commandline.CommandBuilder;
import com.skosarev.task01.dataload.CSVDataLoader;
import com.skosarev.task01.service.StudentService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = ClassLoader.getSystemClassLoader().getResource("students.csv").getPath();
        CommandBuilder commandBuilder = new CommandBuilder(new StudentService(new CSVDataLoader(path)));

        try (Scanner scanner = new Scanner(System.in)) {
            String command;
            do {
                command = scanner.nextLine();
                commandBuilder.solve(command);
            } while (!command.equals("quit"));
        }
    }
}
