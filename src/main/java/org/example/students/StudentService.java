package org.example.students;

import lombok.AllArgsConstructor;
import org.example.students.event.StudentCreatedEvent;
import org.example.students.event.StudentDeletedEvent;
import org.example.students.model.Student;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentService {
    private final Set<Student> students = new HashSet<>();
    private final ApplicationEventPublisher eventPublisher;

    public Set<Student> getAllStudents() {
        return new HashSet<>(students);
    }

    public void addStudent(String lastName, String firstName, int age) {
        Student student = new Student(lastName, firstName, age);
         if (students.add(student)) {
             eventPublisher.publishEvent(new StudentCreatedEvent(this, student));
         }
    }

    public void removeStudentById(int id) {
        if (students.removeIf(student -> student.getId() == id)){
            eventPublisher.publishEvent(new StudentDeletedEvent(this, id));
        }
    }

    public void clearStudents() {
        students.clear();
    }
}
