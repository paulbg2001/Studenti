package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Application;
import ro.ulbs.proiectaresoftware.students.Student;

import java.util.List;

public class StudentiInFisierXlsx implements IStudentiExport {
    private final String fileName;

    public StudentiInFisierXlsx(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void doExport(List<Student> students) {
        Application.exportToExcel(fileName, students);
    }
}
