package com.skosarev.task01;

import com.skosarev.task01.commandline.CommandBuilder;
import com.skosarev.task01.dataload.CSVDataLoader;
import com.skosarev.task01.service.StudentService;

public class Main {
    public static void main(String[] args) {
        String path = ClassLoader.getSystemClassLoader().getResource("students.csv").getPath();
        CommandBuilder commandBuilder = new CommandBuilder(new StudentService(new CSVDataLoader(path)));

        commandBuilder.solve(args[0], args[1]);
    }
}
