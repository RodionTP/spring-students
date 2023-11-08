package org.example.students.event;

import org.example.students.model.Student;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentCreatedEventListener {
    @EventListener
    public void listen(StudentCreatedEvent event) {
        Student student = event.getStudent();
        System.out.println("Создан новый студент:");
        System.out.println("Id: " + student.getId());
        System.out.println("Фамилия: " + student.getLastName());
        System.out.println("Имя: " + student.getFirstName());
        System.out.println("Возраст: " + student.getAge());
    }
}
