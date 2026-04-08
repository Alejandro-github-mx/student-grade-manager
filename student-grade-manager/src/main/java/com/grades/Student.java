package com.grades;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private List<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public boolean isPassing() {
        return getAverage() >= 60.0;
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        String status = isPassing() ? "PASS" : "FAIL";
        return String.format(
            "Student: %-20s | Grades: %-3d | Avg: %6.2f | Status: %s",
            name, grades.size(), getAverage(), status);
    }
}