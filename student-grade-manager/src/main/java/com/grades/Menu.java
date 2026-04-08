package com.grades;
 
import java.util.List;
import java.util.Scanner;
 
public class Menu {
 
    private Scanner scanner;
    private GradeBook gradeBook;
 
    public Menu() {
        this.scanner  = new Scanner(System.in);
        this.gradeBook = new GradeBook();
    }
 
    // ── Entry point for the whole UI loop ──────────────────────────────
    public void run() {
        System.out.println("=== Student Grade Manager ===");
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: handleAddStudent();  break;
                case 2: handleAddGrade();    break;
                case 3: handleShowAverage(); break;
                case 4: handleSearchStudent(); break;
                case 5: handleListAll();     break;
                case 6: running = false;
                        System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid option. Try again.");
            }
            System.out.println();  // Blank line between actions
        }
        scanner.close();  // Always close the Scanner when done
    }
 
    // ── Print the menu options ─────────────────────────────────────────
    private void showMenu() {
        System.out.println("-----------------------------");
        System.out.println("1. Add student");
        System.out.println("2. Add grade to student");
        System.out.println("3. Show student average");
        System.out.println("4. Search student by name");
        System.out.println("5. List all students");
        System.out.println("6. Exit");
        System.out.println("-----------------------------");
    }
 
    // ── Handle: Add Student ───────────────────────────────────────────
    private void handleAddStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        if (gradeBook.studentExists(name)) {
            System.out.println("A student named '" + name + "' already exists.");
            return;
        }
        gradeBook.addStudent(name);
        System.out.println("Student '" + name + "' added successfully.");
    }
 
    // ── Handle: Add Grade ─────────────────────────────────────────────
    private void handleAddGrade() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        Student student = gradeBook.findStudent(name);
        if (student == null) {
            System.out.println("Student '" + name + "' not found.");
            return;
        }
        double grade;
        do {
            grade = readDouble("Enter grade (0–100): ");
            if (grade < 0 || grade > 100) {
                System.out.println("Grade must be between 0 and 100.");
            }
        } while (grade < 0 || grade > 100);  // Repeat until valid
        student.addGrade(grade);
        System.out.printf("Grade %.2f added to %s.%n", grade, name);
    }
 
    // ── Handle: Show Average ──────────────────────────────────────────
    private void handleShowAverage() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        Student student = gradeBook.findStudent(name);
        if (student == null) {
            System.out.println("Student '" + name + "' not found.");
            return;
        }
        if (student.getGrades().isEmpty()) {
            System.out.println(name + " has no grades recorded yet.");
            return;
        }
        System.out.printf("Average for %s: %.2f — %s%n",
            name, student.getAverage(),
            student.isPassing() ? "PASSING" : "FAILING");
    }
 
    // ── Handle: Search Student ────────────────────────────────────────
    private void handleSearchStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        Student student = gradeBook.findStudent(name);
        if (student == null) {
            System.out.println("Student '" + name + "' not found.");
        } else {
            System.out.println(student);  // Calls toString() automatically
        }
    }
 
    // ── Handle: List All ──────────────────────────────────────────────
    private void handleListAll() {
        List<Student> all = gradeBook.getAllStudents();
        if (all.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }
        System.out.println("=== All Students ===");
        for (Student s : all) {
            System.out.println(s);  // Uses toString() from Student
        }
        System.out.println("Total: " + all.size() + " student(s)");
    }
 
    // ── Helper: read integer safely ───────────────────────────────────
    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();  // Discard invalid token
        }
        int value = scanner.nextInt();
        scanner.nextLine();  // Consume leftover newline
        return value;
    }
 
    // ── Helper: read double safely ────────────────────────────────────
    private double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();  // Consume leftover newline
        return value;
    }
}
