package com.skosarev.task01.commandline;

import com.skosarev.task01.dao.StudentDAO;
import com.skosarev.task01.model.Student;
import com.skosarev.task01.util.StudentMapper;

import java.sql.SQLException;
import java.util.List;

public class AverageMarkByLastnameCommand implements Command {
    public static final String NAME = "average-mark-by-lastname";
    private final StudentDAO studentDAO;
    private final String lastname;

    public AverageMarkByLastnameCommand(StudentDAO studentDAO, String lastname) {
        this.studentDAO = studentDAO;
        this.lastname = lastname;
    }

    @Override
    public void execute() throws SQLException {
        List<Student> students = studentDAO.getByLastName(this.lastname).stream().map(StudentMapper::convertToStudent).toList();

        for (Student student : students) {
            System.out.println(student.getFullName() + ", " + student.getGroup() + " — " + student.averageMark());
        }

        if (students.isEmpty()) {
            System.out.println("No result");
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
