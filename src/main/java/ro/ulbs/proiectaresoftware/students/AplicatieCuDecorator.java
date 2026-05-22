package ro.ulbs.proiectaresoftware.students;

import ro.ulbs.proiectaresoftware.students.decorator.TimeExecutionDecorator;
import ro.ulbs.proiectaresoftware.students.strategy.IStudentiExport;
import ro.ulbs.proiectaresoftware.students.strategy.StudentiInConsola;
import ro.ulbs.proiectaresoftware.students.strategy.StudentiInFisierText;
import ro.ulbs.proiectaresoftware.students.strategy.StudentiInFisierXlsx;

import java.util.Arrays;
import java.util.List;

public class AplicatieCuDecorator {
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
        List<IStudentiExport> strategies = Arrays.asList(
                new StudentiInConsola(),
                new StudentiInFisierText("studentiStrategyText.txt"),
                new StudentiInFisierXlsx("studentiStrategyExcel.xls")
        );

        for (IStudentiExport strategy : strategies) {
            TimeExecutionDecorator decorator = new TimeExecutionDecorator(strategy, students);
            long time = decorator.executionTime();
            System.out.println("Execution time: " + time + " ms for " + strategy);
        }
    }
}
