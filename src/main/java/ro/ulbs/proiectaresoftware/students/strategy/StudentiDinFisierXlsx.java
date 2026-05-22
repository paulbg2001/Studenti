package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Application;
import ro.ulbs.proiectaresoftware.students.Student;

import java.util.List;

public class StudentiDinFisierXlsx implements IStudentiImport {
    private final String fileName;

    public StudentiDinFisierXlsx(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Student> doImport() {
        return Application.readFromExcel(fileName);
    }
}
