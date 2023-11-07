package org.example.students.model;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    private static int nextId = 0;

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = ++nextId;
    }
}