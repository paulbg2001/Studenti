package ro.ulbs.proiectaresoftware.students.decorator;

import ro.ulbs.proiectaresoftware.students.Student;
import ro.ulbs.proiectaresoftware.students.strategy.IStudentiExport;

import java.util.List;

public class TimeExecution implements ITimeExecution {
    private final IStudentiExport exporter;

    public TimeExecution(IStudentiExport exporter) {
        this.exporter = exporter;
    }

    @Override
    public long executionTime(List<Student> studenti) {
        long start = System.currentTimeMillis();
        exporter.doExport(studenti);
        return System.currentTimeMillis() - start;
    }
}
