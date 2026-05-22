package ro.ulbs.proiectaresoftware.students;

import ro.ulbs.proiectaresoftware.students.strategy.StudentiInConsola;
import ro.ulbs.proiectaresoftware.students.strategy.StudentiInFisierXlsx;
import ro.ulbs.proiectaresoftware.students.strategy.StudentiDinFisierXlsx;
import ro.ulbs.proiectaresoftware.students.strategy.StudentTransferContext;
import ro.ulbs.proiectaresoftware.students.strategy.StudentiInFisierText;
import ro.ulbs.proiectaresoftware.students.strategy.StudentiDinFisierText;

import java.util.*;

public class AplicatieCuStrategy {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10.0),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10.0),
                new Student(1029, "Maria", "Pana", "TI131/1", 4.10),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22)
        );

        StudentTransferContext context = new StudentTransferContext();

        context.startExport(new StudentiInConsola(), students);

        context.startExport(new StudentiInFisierText("studenti_lab10.txt"), students);

        context.startExport(new StudentiInFisierXlsx("studenti_lab10.xls"), students);

        List<Student> fromText = context.startImport(new StudentiDinFisierText("studenti_lab10.txt"));
        System.out.println("Studenți citiți din TXT: " + fromText.size());

        List<Student> fromExcel = context.startImport(new StudentiDinFisierXlsx("studenti_lab10.xls"));
        System.out.println("Studenți citiți din XLS: " + fromExcel.size());
    }
}
