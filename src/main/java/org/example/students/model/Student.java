package org.example.students.model;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String lastName;
    private String firstName;
    private int age;

    private static int nextId = 0;

    public Student(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.id = ++nextId;
    }
}