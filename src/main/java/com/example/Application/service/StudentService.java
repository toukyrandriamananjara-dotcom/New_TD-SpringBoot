package com.example.Application.service;

import com.example.Application.entity.Student;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public List<Student> addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
        return students;
    }

    public String getStudentNamesAsString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (i > 0) {
                result.append(", ");
            }
            result.append(student.getFirstName()).append(" ").append(student.getLastName());
        }
        return result.toString();
    }
}