package com.skosarev.task01.dataload;

import com.skosarev.task01.dao.StudentDAO;
import com.skosarev.task01.model.Student;
import com.skosarev.task01.util.StudentMapper;

import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;

public class CSVDataLoader implements DataLoader {
    private final String pathname;
    private final StudentDAO studentDAO;

    public CSVDataLoader(String pathname, StudentDAO studentDAO) {
        this.pathname = pathname;
        this.studentDAO = studentDAO;
    }

    @Override
    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathname)))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");

                String lastname = values[0];
                String name = values[1];
                int age = Integer.parseInt(values[2]);
                String group = values[3];
                int[] grades = Arrays.stream(values).skip(4).mapToInt(Integer::parseInt).toArray();
                studentDAO.create(StudentMapper.convertToDTO(new Student(name, lastname, age, group, grades)));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.exit(404);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
