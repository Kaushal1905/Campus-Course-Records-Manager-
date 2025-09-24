package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.Service.CourseService;
import edu.ccrm.Service.EnrollmentService;
import edu.ccrm.Service.StudentService;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
    private static final ImportExportService ioService = new ImportExportService();
    private static final BackupService backupService = new BackupService();
    private static final AppConfig config = AppConfig.getInstance();

    public static void main(String[] args) {
        System.out.println("CCRM Application Initialized. Data Folder: " + config.getDataFolderPath());
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: manageStudents(); break;
                    case 2: manageCourses(); break;
                    case 3: manageEnrollment(); break;
                    case 4: manageDataIO(); break;
                    case 5: manageBackup(); break;
                    case 6: exit = true; System.out.println("Exiting application. Goodbye!"); break;
                    default: System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n--- Campus Course & Records Manager (CCRM) ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollment & Grades");
        System.out.println("4. Import/Export Data");
        System.out.println("5. Backup Data");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void manageStudents() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. List All Students");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: listAllStudents(); break;
                    case 3: back = true; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }
    
    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Registration Number: ");
        String regNo = scanner.nextLine();
        
        Student newStudent = new Student(id, fullName, email, regNo);
        studentService.addStudent(newStudent);
    }
    
    private static void listAllStudents() {
        List<Student> students = studentService.listAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
        } else {
            students.forEach(Student::displayDetails);
        }
    }
    
    private static void manageCourses() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. List All Courses");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: addCourse(); break;
                    case 2: listAllCourses(); break;
                    case 3: back = true; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }
    
    private static void addCourse() {
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Semester (e.g., SPRING, SUMMER, FALL): ");
        String semesterStr = scanner.nextLine().toUpperCase();
        
        try {
            Semester semester = Semester.valueOf(semesterStr);
            Course newCourse = new Course.Builder(code, title)
                .credits(credits)
                .department(department)
                .semester(semester)
                .build();
            courseService.addCourse(newCourse);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid semester. Course not added.");
        }
    }
    
    private static void listAllCourses() {
        List<Course> courses = courseService.listAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses registered yet.");
        } else {
            courses.forEach(System.out::println);
        }
    }
    
    private static void manageEnrollment() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Enrollment & Grading ---");
            System.out.println("1. Enroll Student in a Course");
            System.out.println("2. Record Grade for a Student");
            System.out.println("3. Compute Student GPA");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: enrollStudent(); break;
                    case 2: recordGrade(); break;
                    case 3: computeGpa(); break;
                    case 4: back = true; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }
    
    private static void enrollStudent() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        
        try {
            enrollmentService.enrollStudentInCourse(studentId, courseCode);
        } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException e) {
            System.out.println("Enrollment failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void recordGrade() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter Grade (e.g., A, B, C): ");
        String gradeStr = scanner.nextLine().toUpperCase();
        
        try {
            Grade grade = Grade.valueOf(gradeStr);
            enrollmentService.recordGrade(studentId, courseCode, grade);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid grade entered. Please use a valid grade (S, A, B, C, D, E, F).");
        }
    }
    
    private static void computeGpa() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        double gpa = enrollmentService.computeGPA(studentId);
        System.out.printf("GPA for student %s is: %.2f%n", studentId, gpa);
    }
    
    private static void manageDataIO() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Import/Export Data ---");
            System.out.println("1. Export Students");
            System.out.println("2. Export Courses");
            System.out.println("3. Import Students (from students.csv)");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        try {
                            ioService.exportStudents(config.getDataFolderPath() + "students.csv", studentService);
                        } catch (IOException e) {
                            System.out.println("Error exporting students: " + e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            ioService.exportCourses(config.getDataFolderPath() + "courses.csv", courseService);
                        } catch (IOException e) {
                            System.out.println("Error exporting courses: " + e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            ioService.importStudents(config.getDataFolderPath() + "students.csv", studentService);
                        } catch (IOException e) {
                            System.out.println("Error importing students: " + e.getMessage());
                        }
                        break;
                    case 4: back = true; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }
    
    private static void manageBackup() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Backup Data ---");
            System.out.println("1. Create a Backup");
            System.out.println("2. Calculate Backup Size");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        backupService.createBackup(config.getDataFolderPath(), "backup");
                        break;
                    case 2:
                        System.out.print("Enter backup directory path to calculate size: ");
                        String backupPath = scanner.nextLine();
                        try {
                            long size = backupService.calculateBackupSize(Paths.get(backupPath));
                            System.out.printf("Total size of backup directory is: %.2f KB%n", size / 1024.0);
                        } catch (IOException e) {
                            System.out.println("Error calculating size: " + e.getMessage());
                        }
                        break;
                    case 3: back = true; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }
}