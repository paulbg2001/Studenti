package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Application;
import ro.ulbs.proiectaresoftware.students.Student;

import java.util.List;

public class StudentiInFisierText implements IStudentiExport {
    private final String fileName;

    public StudentiInFisierText(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void doExport(List<Student> students) {
        Application.saveToFile(fileName, students);
    }
}
