package com.skosarev.task01;

import com.skosarev.task01.commandline.CommandBuilder;
import com.skosarev.task01.dataload.CSVDataLoader;
import com.skosarev.task01.service.StudentService;

public class Main {
    public static void main(String[] args) {
        // лучше задавать путь в аргументах запуска, но для удобства (чтобы не вводить каждый раз) вынес сюда
        CommandBuilder cb = new CommandBuilder(new StudentService(new CSVDataLoader(
                ""
        )));

        cb.solve(args[0], args[1]);
    }
}
