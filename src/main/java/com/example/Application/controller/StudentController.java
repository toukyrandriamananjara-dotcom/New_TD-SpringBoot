package com.example.Application.controller;

import com.example.Application.entity.Student;
import com.example.Application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> students) {
        try {
            List<Student> allStudents = studentService.addStudents(students);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(allStudents);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String acceptHeader) {
        try {
            if (acceptHeader == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("L'en-tête 'Accept' est requis.");
            }

            if ("text/plain".equals(acceptHeader)) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(studentService.getStudentNamesAsString());
            }
            else if ("application/json".equals(acceptHeader)) {
                List<Student> allStudents = studentService.getAllStudents();
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(allStudents);
            }
            else {
                return ResponseEntity
                        .status(HttpStatus.NOT_IMPLEMENTED)
                        .body("Format non supporté. Les formats acceptés sont: text/plain et application/json");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur interne est survenue lors du traitement de la requête.");
        }
    }
}