package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public class Student {
    private final int registrationNumber;
    private final String firstName;
    private final String lastName;
    private final String studyGroup;
    private final double grade;

    public Student(int registrationNumber, String firstName, String lastName, String studyGroup) {
        this(registrationNumber, firstName, lastName, studyGroup, 0.0);
    }

    public Student(int registrationNumber, String firstName, String lastName, String studyGroup, double grade) {
        this.registrationNumber = registrationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studyGroup = studyGroup;
        this.grade = grade;
    }

    public Student withGrade(double newGrade) {
        return new Student(this.registrationNumber, this.firstName, this.lastName, this.studyGroup, newGrade);
    }

    public Student withStudyGroup(String newStudyGroup) {
        return new Student(this.registrationNumber, this.firstName, this.lastName, newStudyGroup, this.grade);
    }

    public double getGrade() {
        return grade;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStudyGroup() {
        return studyGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return registrationNumber == student.registrationNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }

    @Override
    public String toString() {
        return String.format("%6d | %20s | %10s | Nota: %5.2f", registrationNumber, firstName + " " + lastName, studyGroup, grade);
    }
}