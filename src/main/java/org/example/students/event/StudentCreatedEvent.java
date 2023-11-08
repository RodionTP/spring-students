package org.example.students.event;

import lombok.Getter;
import org.example.students.model.Student;
import org.springframework.context.ApplicationEvent;

@Getter
public class StudentCreatedEvent extends ApplicationEvent {
    private final Student student;

    public StudentCreatedEvent(Object source, Student student) {
        super(source);
        this.student = student;
    }
}