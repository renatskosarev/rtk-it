package com.skosarev.task01.dao;

import com.skosarev.task01.model.dto.StudentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/rtk";
    private static final String USER = "postgres";
    private static final String PASSWORD = "pass";
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public StudentDTO get(int id) throws SQLException {
        String query = "SELECT * FROM students s LEFT JOIN marks m on s.id = m.student_id LEFT JOIN groups g on g.id = s.group_id WHERE s.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            StudentDTO studentDTO = null;
            if (resultSet.next()) {
                studentDTO = getNextFromRS(resultSet);
            }
            return studentDTO;
        }
    }

    public List<StudentDTO> getAll() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("SELECT * FROM students LEFT JOIN marks ON students.id = marks.student_id LEFT JOIN groups ON students.group_id = groups.id;");
            ResultSet resultSet = statement.getResultSet();

            List<StudentDTO> studentDTOs = new ArrayList<>();
            while (resultSet.next()) {
                studentDTOs.add(getNextFromRS(resultSet));
            }

            return studentDTOs;
        }
    }

    /**
     * Храню данные ученика, группы и оценки в разных таблицах, поэтому пришлось делать несколько запросов
     * (вытягивание данных, проверка на существование группы)
     */
    public void create(StudentDTO studentDTO) throws SQLException {
        int groupId = getGroupIdByName(studentDTO.getGroup());

        if (groupId == -1) {
            groupId = insertGroup(studentDTO.getGroup());
        }

        int studentId = insertStudent(studentDTO, groupId);

        insertMarks(studentId, studentDTO.getMathematics(), studentDTO.getGeometry(), studentDTO.getInformatics(),
                studentDTO.getPhysics(), studentDTO.getRussian(), studentDTO.getLiterature());
    }

    /**
     * @return group ID or -1 if group not exists
     */
    public int getGroupIdByName(String groupName) throws SQLException {
        String checkGroupExistsSQL = "SELECT id FROM groups WHERE group_name = ?";
        try (PreparedStatement checkGroupStatement = connection.prepareStatement(checkGroupExistsSQL)) {
            checkGroupStatement.setString(1, groupName);
            checkGroupStatement.execute();
            ResultSet resultSet = checkGroupStatement.getResultSet();
            int groupId = -1;
            if (resultSet.next()) {
                groupId = resultSet.getInt(1);
            }

            return groupId;
        }
    }

    /**
     * @return created group ID
     */
    public int insertGroup(String groupName) throws SQLException {
        String groupSQL = "INSERT INTO groups(group_name) VALUES (?)";
        try (PreparedStatement groupStatement = connection.prepareStatement(groupSQL, Statement.RETURN_GENERATED_KEYS)) {
            groupStatement.setString(1, groupName);
            groupStatement.executeUpdate();
            ResultSet resultSet = groupStatement.getGeneratedKeys();

            int groupId = -1;
            if (resultSet.next()) {
                groupId = resultSet.getInt(1);
            }

            return groupId;
        }
    }

    /**
     *
     * @return Created student ID
     */
    public int insertStudent(StudentDTO studentDTO, int groupId) throws SQLException {
        String studentSQL = "INSERT INTO students(first_name, last_name, age, group_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement studentStatement = connection.prepareStatement(studentSQL, Statement.RETURN_GENERATED_KEYS)) {
            studentStatement.setString(1, studentDTO.getFirstName());
            studentStatement.setString(2, studentDTO.getLastName());
            studentStatement.setInt(3, studentDTO.getAge());
            studentStatement.setInt(4, groupId);
            studentStatement.executeUpdate();
            ResultSet resultSet = studentStatement.getGeneratedKeys();
            int studentId = -1;
            if (resultSet.next()) {
                studentId = resultSet.getInt(1);
            }

            return studentId;
        }
    }

    public void insertMarks(int studentId, int math, int geometry, int informatics, int physics, int russian, int literature) throws SQLException {
        String query = "INSERT INTO marks (student_id, math, geometry, informatics, physics, russian, literature) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement marksStatement = connection.prepareStatement(query)) {
            marksStatement.setInt(1, studentId);
            marksStatement.setInt(2, math);
            marksStatement.setInt(3, geometry);
            marksStatement.setInt(4, informatics);
            marksStatement.setInt(5, physics);
            marksStatement.setInt(6, russian);
            marksStatement.setInt(7, literature);
            marksStatement.executeUpdate();
        }
    }


    public void delete(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeQuery();
        }
    }


    // COMMANDS

    public List<StudentDTO> getWhereGroupIs(String... groups) throws SQLException {
        String part = "?, ".repeat(groups.length);
        part = part.substring(0, part.length() - 2);
        String query = "SELECT * FROM students LEFT JOIN groups ON students.group_id = groups.id LEFT JOIN marks ON students.id = marks.student_id WHERE group_name IN (" + part + ")";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (int i = 1; i <= groups.length; i++) {
                preparedStatement.setString(i, groups[i - 1]);
            }

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            List<StudentDTO> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getNextFromRS(resultSet));
            }

            return result;
        }
    }

    public List<StudentDTO> getOlderThan(int startAge) throws SQLException {
        String query = "SELECT * FROM students LEFT JOIN groups ON students.group_id = groups.id LEFT JOIN marks ON students.id = marks.student_id WHERE age >= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, startAge);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            List<StudentDTO> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getNextFromRS(resultSet));
            }

            return result;
        }
    }

    public List<StudentDTO> getByLastName(String lastName) throws SQLException {
        String query = "SELECT * FROM students LEFT JOIN groups ON students.group_id = groups.id LEFT JOIN marks ON students.id = marks.student_id WHERE last_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            List<StudentDTO> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(getNextFromRS(resultSet));
            }

            return result;
        }
    }

    private StudentDTO getNextFromRS(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int age = resultSet.getInt("age");
        String group = resultSet.getString("group_name");
        int math = resultSet.getInt("math");
        int geometry = resultSet.getInt("geometry");
        int informatics = resultSet.getInt("informatics");
        int physics = resultSet.getInt("physics");
        int russian = resultSet.getInt("russian");
        int literature = resultSet.getInt("literature");

        return new StudentDTO(firstName, lastName, age, group,
                math, geometry, informatics, physics, russian, literature);
    }
}
