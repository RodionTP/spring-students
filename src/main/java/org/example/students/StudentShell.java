package org.example.students;

import lombok.AllArgsConstructor;
import org.example.students.model.Student;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.text.MessageFormat;
import java.util.Random;
import java.util.Set;

@ShellComponent
@AllArgsConstructor
public class StudentShell {
    private final StudentService studentService;

    @ShellMethod(key = "p")
    public void printStudents() {
        Set<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст!");
        } else {
            students.forEach(System.out::println);
        }
    }

    @ShellMethod(key = "a")
    public void addStudent(@ShellOption(value = "l") String lastName,
                             @ShellOption(value = "f") String firstName,
                             @ShellOption(value = "a") int age) {
        studentService.addStudent(lastName, firstName, age);
    }

    @ShellMethod(key = "r")
    @ShellMethodAvailability("canRemoveStudent")
    public void removeStudentById(@ShellOption(value = "i") int id) {
        studentService.removeStudentById(id);
    }

    @ShellMethod(key = "c")
    @ShellMethodAvailability("canRemoveStudent")
    public String clearStudents() {
        studentService.clearStudents();
        return "База студентов очищена";
    }

    @ShellMethod(key = "i")
    public String init(@ShellOption(value = "c") int count) {
        for (int i = 0; i < count; ) {
            studentService.addStudent("LastName_" + ++i,
                    "FirstName_" + i,
                    new Random().nextInt(15, 31));
        }
        return MessageFormat.format("Добавлено {0} студентов", count);
    }

    public Availability canRemoveStudent() {
        return studentService.getAllStudents().isEmpty() ? Availability.unavailable("Список студентов пуст!") : Availability.available();
    }
}