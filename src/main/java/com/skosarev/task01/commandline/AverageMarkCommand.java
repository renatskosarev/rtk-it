package com.skosarev.task01.commandline;

import com.skosarev.task01.dao.StudentDAO;
import com.skosarev.task01.model.Student;
import com.skosarev.task01.util.StudentMapper;

import java.sql.SQLException;
import java.util.List;

public class AverageMarkCommand implements Command {
    private static final String NAME = "average-mark";
    private final StudentDAO studentDAO;
    private final String[] groups;

    public AverageMarkCommand(StudentDAO studentDAO, String... groups) {
        this.studentDAO = studentDAO;
        this.groups = groups;
    }

    @Override
    public void execute() throws SQLException {
        double sum = 0;

        List<Student> students = studentDAO.getWhereGroupIs(this.groups).stream().map(StudentMapper::convertToStudent).toList();
        for (Student student : students) {
            sum += student.averageMark();
        }

        if (students.isEmpty()) {
            System.out.println("No students");
        } else {
            System.out.println("Average mark: " + sum / students.size());
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
