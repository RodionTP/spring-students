package org.example.students.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentDeletedEventListener {
    @EventListener
    public void listen(StudentDeletedEvent event) {
        int id = event.getId();
        System.out.println("Удален студент с идентификатором: " + id);
    }
}
