package com.skosarev.task01.util;

import com.skosarev.task01.model.dto.StudentDTO;
import com.skosarev.task01.model.Student;

public class StudentMapper {
    private StudentMapper() {}

    public static StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getFirstName(),
                student.getLastName(),
                student.getAge(),
                student.getGroup(),
                student.getMathematics(),
                student.getGeometry(),
                student.getInformatics(),
                student.getPhysics(),
                student.getRussian(),
                student.getLiterature()
        );
    }

    public static Student convertToStudent(StudentDTO studentDTO) {
        return new Student(
                studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getAge(),
                studentDTO.getGroup(),
                studentDTO.getMathematics(),
                studentDTO.getGeometry(),
                studentDTO.getInformatics(),
                studentDTO.getPhysics(),
                studentDTO.getRussian(),
                studentDTO.getLiterature()
        );
    }
}
