package ro.ulbs.proiectaresoftware.students;

import java.io.*;
import java.util.*;


interface StudentExportStrategy {
    void doExport(List<Student> students);
}

interface StudentImportStrategy {
    List<Student> doImport();
}

class StudentTransferContext {
    void startExport(StudentExportStrategy strategy, List<Student> students) {
        strategy.doExport(students);
    }

    List<Student> startImport(StudentImportStrategy strategy) {
        return strategy.doImport();
    }
}


class ConsoleExportStrategy implements StudentExportStrategy {
    @Override
    public void doExport(List<Student> students) {
        System.out.println("\n--- [Strategy] Afișare în Consolă ---");
        students.forEach(System.out::println);
    }
}

class TextFileExportStrategy implements StudentExportStrategy {
    private final String fileName;
    public TextFileExportStrategy(String fileName) { this.fileName = fileName; }

    @Override
    public void doExport(List<Student> students) {
        Application.saveToFile(fileName, students);
    }
}

class ExcelFileExportStrategy implements StudentExportStrategy {
    private final String fileName;
    public ExcelFileExportStrategy(String fileName) { this.fileName = fileName; }

    @Override
    public void doExport(List<Student> students) {
        Application.exportToExcel(fileName, students);
    }
}

class TextFileImportStrategy implements StudentImportStrategy {
    private final String fileName;
    public TextFileImportStrategy(String fileName) { this.fileName = fileName; }

    @Override
    public List<Student> doImport() {
        List<Student> readStudents = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                if (tokens.length >= 4) {
                    readStudents.add(new Student(Integer.parseInt(tokens[0].trim()), tokens[1].trim(), tokens[2].trim(), tokens[3].trim()));
                }
            }
            System.out.println("Import TXT reușit din: " + fileName);
        } catch (Exception e) {
            System.out.println("Eroare import TXT: " + e.getMessage());
        }
        return readStudents;
    }
}

class ExcelFileImportStrategy implements StudentImportStrategy {
    private final String fileName;
    public ExcelFileImportStrategy(String fileName) { this.fileName = fileName; }

    @Override
    public List<Student> doImport() {
        return Application.readFromExcel(fileName);
    }
}

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

        context.startExport(new ConsoleExportStrategy(), students);

        context.startExport(new TextFileExportStrategy("studenti_lab10.txt"), students);

        context.startExport(new ExcelFileExportStrategy("studenti_lab10.xls"), students);

        List<Student> fromText = context.startImport(new TextFileImportStrategy("studenti_lab10.txt"));
        System.out.println("Studenți citiți din TXT: " + fromText.size());

        List<Student> fromExcel = context.startImport(new ExcelFileImportStrategy("studenti_lab10.xls"));
        System.out.println("Studenți citiți din XLS: " + fromExcel.size());
    }
}
