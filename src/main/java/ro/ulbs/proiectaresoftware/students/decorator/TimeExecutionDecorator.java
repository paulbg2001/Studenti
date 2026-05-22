package ro.ulbs.proiectaresoftware.students.decorator;

import ro.ulbs.proiectaresoftware.students.Student;
import ro.ulbs.proiectaresoftware.students.strategy.IStudentiExport;

import java.util.List;

public class TimeExecutionDecorator extends TimeExecution {
    private List<Student> studentList;

    public TimeExecutionDecorator(IStudentiExport studentExporter, List<Student> studentList) {
        super(studentExporter);
        this.studentList = studentList;
    }

    public long executionTime() {
        long execTime = super.executionTime(studentList);
        return execTime;
    }
}
