package org.example.students;

import org.example.students.model.Student;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@ShellComponent
public class StudentShell {
    Set<Student> students = new HashSet<>();

    @ShellMethod(key = "p")
    public void printStudents() {
        if (students.isEmpty()) {
            System.out.println("Студентов в списке нет");
        } else {
            students.forEach(System.out::println);
        }
    }

    @ShellMethod(key = "a")
    public String addStudent(@ShellOption(value = "l") String lastName,
                              @ShellOption(value = "f") String firstName,
                              @ShellOption(value = "a") int age) {
        return students.add(new Student(lastName, firstName, age)) ? "Студент добавлен" : "Не удалось добавить";
    }

    @ShellMethod(key = "rId")
    @ShellMethodAvailability("canRemoveStudent")
    public String removeStudentById(int id) {
        return students.removeIf(student -> student.getId() == id) ? "Студент удален" : "Нет такого студента";
    }

    @ShellMethod(key = "rAll")
    @ShellMethodAvailability("canRemoveStudent")
    public String removeAllStudents() {
        students.clear();
        return "База студентов очищена";
    }

    @ShellMethod(key = "i")
    public String init(@ShellOption(value = "c") int count) {
        for (int i = 0; i < count; ){
            students.add(new Student("LastName_" + ++i, "FirstName_" + i, new Random().nextInt(15,31)));
        }
        return MessageFormat.format("Добавлено {0} студентов", count);
    }

    public Availability canRemoveStudent() {
        return students.isEmpty() ? Availability.unavailable("Список студентов пуст!") : Availability.available();
    }
}