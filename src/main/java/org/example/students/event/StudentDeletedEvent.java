package org.example.students.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StudentDeletedEvent extends ApplicationEvent {
    private final int id;

    public StudentDeletedEvent(Object source, int id) {
        super(source);
        this.id = id;
    }
}
