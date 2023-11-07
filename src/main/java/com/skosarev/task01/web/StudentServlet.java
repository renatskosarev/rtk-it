package com.skosarev.task01.web;

import com.skosarev.task01.dao.StudentDAO;
import com.skosarev.task01.model.Student;
import com.skosarev.task01.util.StudentMapper;
import lombok.SneakyThrows;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAO();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String group = req.getParameter("group");
        List<Student> students = studentDAO.getAllByGroupName(group).stream().map(StudentMapper::convertToStudent).toList();

        try (var output = resp.getWriter()) {
            output.write(String.format("<h1>Группа: %s</h1>", group));
            for (Student student : students) {
                output.write(String.format("%s — %f<br>", student.getFullName(), student.averageMark()));
            }
            if (students.isEmpty()) {
                output.write("Нет студентов");
            }
            output.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");

        try (var output = resp.getWriter()) {
            String name = req.getParameter("name");
            String group = req.getParameter("group");
            String subject = req.getParameter("subject");
            int newMark = -1;
            try {
                newMark = Integer.parseInt(req.getParameter("newMark"));
            } catch (NumberFormatException e) {
                output.write("Parameter 'newMark' should be not empty");
            }

            if (name == null || group == null || subject == null || newMark < 1 || newMark > 5) {
                output.write("Parameters 'name', 'group', 'subject' should be not empty;<br>");
                output.write("'newMark' should be between 1 and 5");

            } else {
                try {
                    studentDAO.setNewMark(name, group, subject, newMark);
                } catch (SQLException e) {
                    output.write("Error");
                }
            }
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
