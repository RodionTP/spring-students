package org.example.students;

import org.example.students.model.Student;
import org.springframework.core.env.Environment;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.Random;
import java.util.Set;

@ShellComponent
public class StudentShell {
    private final StudentService studentService;
    private final boolean createStudents;
    private final int countStudentsToCreate;

    public StudentShell(StudentService studentService, Environment environment) {
        this.studentService = studentService;
        this.createStudents = Boolean.parseBoolean(environment.getProperty("CREATE_STUDENTS"));
        this.countStudentsToCreate = Integer.parseInt(environment.getProperty("COUNT_STUDENTS_CREATE", "0"));
        if (createStudents) {
            initializeStudents(countStudentsToCreate);
        }
    }

    private void initializeStudents(int count) {
        for (int i = 0; i < count; ) {
            studentService.addStudent("LastName_" + ++i,
                    "FirstName_" + i,
                    new Random().nextInt(15, 31));
        }
    }

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

    public Availability canRemoveStudent() {
        return studentService.getAllStudents().isEmpty() ? Availability.unavailable("Список студентов пуст!") : Availability.available();
    }
}