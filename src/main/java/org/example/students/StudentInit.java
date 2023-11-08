package org.example.students;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@ConditionalOnProperty("app.createStudentsOnStartup")
public class StudentInit {
    private final StudentService studentService;

    public StudentInit(StudentService studentService) {
        this.studentService = studentService;
    }

    @Value("${app.countStudentsToCreate}")
    private int countStudentsToCreate;

    @PostConstruct
    public void initializeStudents() {
        for (int i = 0; i < countStudentsToCreate; ) {
            studentService.addStudent("LastName_" + ++i,
                    "FirstName_" + i,
                    new Random().nextInt(15, 31));
        }
    }
}
