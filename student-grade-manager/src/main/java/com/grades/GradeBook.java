package com.grades;
 
import java.util.ArrayList;
import java.util.List;
 
public class GradeBook {
 
    private List<Student> students;
 
    public GradeBook() {
        this.students = new ArrayList<>();
    }
 
    // Create and add a new student
    public void addStudent(String name) {
        students.add(new Student(name));
    }
 
    // Find a student by name (case-insensitive). Returns null if not found.
    public Student findStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;  // Found — return immediately
            }
        }
        return null;  // Not found — equivalent to Python's None
    }
 
    // Check if a student already exists (used to prevent duplicates)
    public boolean studentExists(String name) {
        return findStudent(name) != null;
    }
 
    // Return the full list (used by Menu to display all students)
    public List<Student> getAllStudents() {
        return students;
    }
}
