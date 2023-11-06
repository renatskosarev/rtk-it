package com.skosarev.task01;

import com.skosarev.task01.commandline.CommandBuilder;
import com.skosarev.task01.dao.StudentDAO;
import com.skosarev.task01.dataload.CSVDataLoader;
import com.skosarev.task01.service.StudentService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = ClassLoader.getSystemClassLoader().getResource("students.csv").getPath();
        StudentDAO dao = new StudentDAO();
        StudentService studentService = new StudentService(new CSVDataLoader(path, dao), dao);
        CommandBuilder commandBuilder = new CommandBuilder(studentService);

        // Загрузить данные в БД:
//        studentService.loadData();


        System.out.println("Ready to execute commands");
        try (Scanner scanner = new Scanner(System.in)) {
            String command;
            do {
                command = scanner.nextLine();
                commandBuilder.solve(command);
            } while (!command.equals("quit"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
