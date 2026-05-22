package ro.ulbs.proiectaresoftware.students.strategy;

import ro.ulbs.proiectaresoftware.students.Student;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentiDinFisierText implements IStudentiImport {
    private final String fileName;

    public StudentiDinFisierText(String fileName) {
        this.fileName = fileName;
    }

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
