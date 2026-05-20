package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public class StudentBursieri extends Student {
    private final double scholarshipAmount;

    public StudentBursieri(int registrationNumber, String firstName, String lastName, String studyGroup, double grade, double scholarshipAmount) {
        super(registrationNumber, firstName, lastName, studyGroup, grade);
        this.scholarshipAmount = scholarshipAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentBursieri that = (StudentBursieri) o;
        return Double.compare(that.scholarshipAmount, scholarshipAmount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), scholarshipAmount);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Bursa: %7.2f", scholarshipAmount);
    }

    public double getScholarshipAmount() {
        return scholarshipAmount;
    }
}
