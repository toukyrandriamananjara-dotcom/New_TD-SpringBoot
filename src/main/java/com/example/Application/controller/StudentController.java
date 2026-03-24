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
    public String addStudents(@RequestBody List<Student> students) {
            studentService.addStudents(students);

        return studentService.getStudentNamesAsString();
    }

    @GetMapping("/students")
    public ResponseEntity<String> getStudents(@RequestHeader("Accept") String acceptHeader) {
        if ("text/plain".equals(acceptHeader)) {
            return ResponseEntity.ok(studentService.getStudentNamesAsString());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Format non supporté.");
        }
    }
}