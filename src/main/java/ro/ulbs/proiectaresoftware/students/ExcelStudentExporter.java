package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public class ExcelStudentExporter implements StudentExporter {
    @Override
    public void export(String fileName, List<Student> students) {
        Application.exportToExcel(fileName, students);
    }
}
