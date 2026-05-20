package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AplicatieCuBursa {

    public static void main(String[] args) {
        AplicatieCuBursa instance = new AplicatieCuBursa();
        List<StudentBursieri> students = instance.generate();

        System.out.println("Lista initiala:");
        for (StudentBursieri student : students) {
            System.out.println(student);
        }

        System.out.println("Lista sortata (Formatie -> Nume -> Prenume -> Nota -> Bursa):");
        List<StudentBursieri> sortedStudents = instance.sort(students);
        for (StudentBursieri student : sortedStudents) {
            System.out.println(student);
        }
    }

    public List<StudentBursieri> generate() {
        List<StudentBursieri> students = new ArrayList<>();
        students.add(new StudentBursieri(1025, "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
        students.add(new StudentBursieri(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
        students.add(new StudentBursieri(1029, "Bianca", "Popescu", "TI131/1", 9.10, 780.80));
        students.add(new StudentBursieri(1026, "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
        students.add(new StudentBursieri(1029, "Bianca", "Popescu", "TI131/1", 9.10, 100.00));
        return students;
    }

    public List<StudentBursieri> sort(List<StudentBursieri> students) {
        List<StudentBursieri> sortedStudents = new ArrayList<>(students);

        sortedStudents.sort(Comparator
                .comparing(StudentBursieri::getStudyGroup)
                .thenComparing(StudentBursieri::getLastName)
                .thenComparing(StudentBursieri::getFirstName)
                .thenComparing(StudentBursieri::getGrade)
                .thenComparing(StudentBursieri::getScholarshipAmount));

        return sortedStudents;
    }
}
