package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Student;

import java.util.List;

public class StudentiInConsola implements IStudentiExport {
    @Override
    public void doExport(List<Student> students) {
        System.out.println("\n--- [Strategy] Afișare în Consolă ---");
        students.forEach(System.out::println);
    }
}
