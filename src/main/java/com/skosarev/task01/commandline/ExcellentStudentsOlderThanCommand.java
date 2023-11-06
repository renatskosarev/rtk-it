package com.skosarev.task01.commandline;

import com.skosarev.task01.dao.StudentDAO;
import com.skosarev.task01.model.Student;
import com.skosarev.task01.util.StudentMapper;

import java.sql.SQLException;
import java.util.List;

public class ExcellentStudentsOlderThanCommand implements Command {
    private static final String NAME = "excellent-students-older-than";
    private final StudentDAO studentDAO;
    private final int startAge;

    public ExcellentStudentsOlderThanCommand(StudentDAO studentDAO, int startAge) {
        this.studentDAO = studentDAO;
        this.startAge = startAge;
    }

    @Override
    public void execute() throws SQLException {
        System.out.println("Excellent students age " + this.startAge + "+:");

        List<Student> students = studentDAO.getOlderThan(this.startAge).stream().map(StudentMapper::convertToStudent).toList();
        for (Student student : students) {
            if (student.isExcellentStudent()) {
                System.out.println(student);
            }
        }

        if (students.isEmpty()) {
            System.out.println("No students");
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
