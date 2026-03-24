package com.example.Application.service;

import com.example.Application.entity.Student;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public List<Student> addStudents(List<Student> newStudents) {
        if (newStudents == null) {
            throw new IllegalArgumentException("La liste des étudiants ne peut pas être null");
        }

        for (Student student : newStudents) {
            if (student.getReference() == null || student.getReference().trim().isEmpty()) {
                throw new IllegalArgumentException("La référence de l'étudiant est requise");
            }
            if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
                throw new IllegalArgumentException("Le prénom de l'étudiant est requis");
            }
            if (student.getLastName() == null || student.getLastName().trim().isEmpty()) {
                throw new IllegalArgumentException("Le nom de l'étudiant est requis");
            }
            if (student.getAge() <= 0) {
                throw new IllegalArgumentException("L'âge de l'étudiant doit être supérieur à 0");
            }
        }

        students.addAll(newStudents);
        return new ArrayList<>(students);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
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