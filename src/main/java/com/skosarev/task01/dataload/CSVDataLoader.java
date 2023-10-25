package com.skosarev.task01.dataload;

import com.skosarev.task01.model.Person;
import com.skosarev.task01.datastructures.DataGroup;

import java.io.*;
import java.util.Arrays;

public class CSVDataLoader implements DataLoader {
    private final String pathname;

    public CSVDataLoader(String pathname) {
        this.pathname = pathname;
    }

    @Override
    public void loadData(DataGroup dataGroup) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathname)))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");

                String lastname = values[0];
                String name = values[1];
                int age = Integer.parseInt(values[2]);
                int group = Integer.parseInt(values[3]);
                int[] grades = Arrays.stream(values).skip(4).mapToInt(Integer::parseInt).toArray();

                dataGroup.addPerson(new Person(name, lastname, age, group, grades));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.exit(404);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
