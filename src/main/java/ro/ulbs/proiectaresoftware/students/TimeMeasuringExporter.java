package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public class TimeMeasuringExporter implements StudentExporter {
    private final StudentExporter wrappedExporter;

    public TimeMeasuringExporter(StudentExporter wrappedExporter) {
        this.wrappedExporter = wrappedExporter;
    }

    @Override
    public void export(String fileName, List<Student> students) {
        long startTime = System.currentTimeMillis();

        wrappedExporter.export(fileName, students);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Timpul de executie pentru export a fost de: " + duration + " ms.");
    }
}
