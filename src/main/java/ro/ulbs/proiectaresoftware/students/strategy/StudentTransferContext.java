package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Student;

import java.util.List;

public class StudentTransferContext {
    public void startExport(IStudentiExport strategy, List<Student> students) {
        strategy.doExport(students);
    }

    public List<Student> startImport(IStudentiImport strategy) {
        return strategy.doImport();
    }
}
