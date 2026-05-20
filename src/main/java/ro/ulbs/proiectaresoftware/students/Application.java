package ro.ulbs.proiectaresoftware.students;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class Application {

    public static void saveToFile(String fileName, Collection<? extends Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.println(student.toString());
            }
            System.out.println("Salvare reusita! Colectia a fost scrisa in fisierul: " + fileName);
        } catch (IOException e) {
            System.out.println("A aparut o eroare la scrierea in fisierul " + fileName + ": " + e.getMessage());
        }
    }

    public static float findGrade(String firstName, String lastName, Map<Integer, Student> studentMap) {
        HashMap<String, Student> lookupMap = new HashMap<>();
        for (Student student : studentMap.values()) {
            String key = student.getFirstName() + "-" + student.getLastName();
            lookupMap.put(key, student);
        }

        String searchKey = firstName + "-" + lastName;
        Student foundStudent = lookupMap.get(searchKey);

        if (foundStudent != null) {
            return (float) foundStudent.getGrade();
        }
        return 0.0f;
    }

    public static List<Student> splitIntoTwoGroups(List<Student> students) {
        List<Student> updatedList = new ArrayList<>();
        int size = students.size();
        int midPoint = (size + 1) / 2;

        for (int i = 0; i < size; i++) {
            Student student = students.get(i);
            String newGroup = (i < midPoint) ? "Formatia_A" : "Formatia_B";

            updatedList.add(student.withStudyGroup(newGroup));
        }
        return updatedList;
    }

    public static void exportToExcel(String fileName, List<Student> students) {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("Studenti");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Matricol");
            headerRow.createCell(1).setCellValue("Prenume");
            headerRow.createCell(2).setCellValue("Nume");
            headerRow.createCell(3).setCellValue("Formatie");

            int rowIndex = 1;
            for (Student student : students) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(student.getRegistrationNumber());
                row.createCell(1).setCellValue(student.getFirstName());
                row.createCell(2).setCellValue(student.getLastName());
                row.createCell(3).setCellValue(student.getStudyGroup());
            }

            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
            }
            System.out.println("Export Excel reusit in: " + fileName);

        } catch (IOException e) {
            System.out.println("Eroare la exportul in Excel: " + e.getMessage());
        }
    }

    public static List<Student> readFromExcel(String fileName) {
        List<Student> readStudents = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(new File(fileName));
             HSSFWorkbook workbook = new HSSFWorkbook(inputStream)) {

            HSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    int registrationNumber = (int) row.getCell(0).getNumericCellValue();
                    String firstName = row.getCell(1).getStringCellValue();
                    String lastName = row.getCell(2).getStringCellValue();
                    String studyGroup = row.getCell(3).getStringCellValue();

                    Student student = new Student(registrationNumber, firstName, lastName, studyGroup);
                    readStudents.add(student);
                }
            }
            System.out.println("Import Excel reusit din: " + fileName);

        } catch (IOException e) {
            System.out.println("Eroare la citirea din Excel: " + e.getMessage());
        }

        return readStudents;
    }

    public static void main(String[] args) {
        HashMap<Integer, Student> studentMap = new HashMap<>();

        try {
            File studentFile = new File("studenti.txt");
            Scanner scanner = new Scanner(studentFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                int registrationNumber = Integer.parseInt(tokens[0].trim());
                String firstName = tokens[1].trim();
                String lastName = tokens[2].trim();
                String studyGroup = tokens[3].trim();

                Student student = new Student(registrationNumber, firstName, lastName, studyGroup);
                studentMap.put(registrationNumber, student);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul cu studenti.txt nu a fost gasit!");
        }

        try {
            File gradesFile = new File("note_anon.txt");
            Scanner gradesScanner = new Scanner(gradesFile);

            while (gradesScanner.hasNextLine()) {
                String line = gradesScanner.nextLine();
                String[] tokens = line.split(",");

                int registrationNumber = Integer.parseInt(tokens[0].trim());
                double grade = Double.parseDouble(tokens[1].trim());

                Student student = studentMap.get(registrationNumber);
                if (student != null) {
                    Student updatedStudent = student.withGrade(grade);
                    studentMap.put(registrationNumber, updatedStudent);
                }
            }
            gradesScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul note_anon.txt nu a fost gasit!");
        }

        System.out.println("Catalog Studenti:");
        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println("\n--- Sectiune Bursieri ---");

        List<StudentBursieri> scholarshipStudents = new ArrayList<>();
        scholarshipStudents.add(new StudentBursieri(1025, "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
        scholarshipStudents.add(new StudentBursieri(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
        scholarshipStudents.add(new StudentBursieri(1026, "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
        scholarshipStudents.add(new StudentBursieri(1029, "Bianca", "Popescu", "TI131/1", 9.10, 780.80));

        saveToFile("bursieri_out.txt", scholarshipStudents);

        System.out.println("\n--- Sectiune 3.5.2 (Citire din studenti_in.txt) ---");

        List<Student> newStudentList = new ArrayList<>();

        try {
            File inputFile = new File("studenti_in.txt");
            Scanner inputScanner = new Scanner(inputFile);

            System.out.println("Studentii cititi sunt:");
            while (inputScanner.hasNextLine()) {
                String line = inputScanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] tokens = line.split(",");
                int registrationNumber = Integer.parseInt(tokens[0].trim());
                String firstName = tokens[1].trim();
                String lastName = tokens[2].trim();
                String studyGroup = tokens[3].trim();

                Student student = new Student(registrationNumber, firstName, lastName, studyGroup);
                newStudentList.add(student);

                System.out.println(student);
            }
            inputScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul studenti_in.txt nu a fost gasit!");
        }

        newStudentList.sort(Comparator.comparing(Student::getLastName));
        saveToFile("studenti_out.txt", newStudentList);

        System.out.println("\n--- Sectiune 3.5.3 (Sortare Multipla) ---");
        newStudentList.sort(
                Comparator.comparing(Student::getStudyGroup)
                        .thenComparing(Student::getLastName)
        );
        saveToFile("studenti_out_sorted.txt", newStudentList);

        System.out.println("\n--- Sectiune 4.5.3 (Cautare O(1)) ---");
        float gradeM = findGrade("Bianca", "Popescu", studentMap);
        float gradeN = findGrade("Ioan", "Popa", studentMap);
        System.out.println("notaM (Bianca Popescu) = " + gradeM);
        System.out.println("notaN (Ioan Popa) = " + gradeN);

        System.out.println("\n--- Sectiune 7.6.3 (Impartire in 2 formatii de studiu) ---");

        List<Student> splitStudents = splitIntoTwoGroups(newStudentList);

        System.out.println("Lista NOUA dupa mutarea studentilor in noile formatii (Formatiile vechi au ramas nemodificate):");
        for (Student student : splitStudents) {
            System.out.println(student);
        }

        System.out.println("\n--- Sectiune 8.5.4 a) Export Lista Studenti in Excel (cu Decorator pt Timp) ---");
        String excelFileName = "laborator8_students.xls";

        StudentExporter baseExporter = new ExcelStudentExporter();

        StudentExporter timedExporter = new TimeMeasuringExporter(baseExporter);

        timedExporter.export(excelFileName, newStudentList);

        System.out.println("\n--- Sectiune 8.5.4 b) Import Colectie Studenti din Excel ---");
        List<Student> studentsFromExcel = readFromExcel(excelFileName);
        System.out.println("Afisare studenti cititi din fisierul .xls:");
        for (Student student : studentsFromExcel) {
            System.out.println(student);
        }

        System.out.println("\n Sectiune 9.3.3 ");

        List<Student> gradedStudents = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10.0),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10.0),
                new Student(1029, "Maria", "Pana", "TI131/2", 4.10),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22)
        );

        System.out.println("\na) Studenti cu nota 10:");
        gradedStudents.stream()
                .filter(s -> s.getGrade() == 10.0)
                .forEach(System.out::println);

        System.out.println("\nb) Studenti cu nota sub 5:");
        gradedStudents.stream()
                .filter(s -> s.getGrade() < 5.0)
                .forEach(System.out::println);

        System.out.println("\nc) Lista transformata (studentii cu nota < 4 devin cu nota 4):");
        List<Student> transformedStudents = gradedStudents.stream()
                .map(s -> s.getGrade() < 4.0 ? s.withGrade(4.0) : s)
                .collect(Collectors.toList());

        transformedStudents.forEach(System.out::println);

        System.out.println("\nd) Suma notelor tuturor studentilor:");
        double gradeSum = gradedStudents.stream()
                .map(Student::getGrade)
                .reduce(0.0, (accumulator, grade) -> accumulator + grade);
        System.out.println(String.format("%.2f", gradeSum));

        System.out.println("\ne) Media notelor studentilor din lista:");
        double averageGrade = gradeSum / gradedStudents.size();
        System.out.println(String.format("%.2f", averageGrade));
    }

}
