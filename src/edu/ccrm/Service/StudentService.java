package edu.ccrm.Service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentService {
    private final List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        boolean isDuplicate = students.stream()
            .anyMatch(s -> s.getId().equals(student.getId()) || s.getRegNo().equals(student.getRegNo()));

        if (isDuplicate) {
            System.out.println("Error: A student with the same ID or registration number already exists.");
            return false;
        }

        students.add(student);
        System.out.println("Student " + student.getFullName() + " added successfully.");
        return true;
    }

    public Optional<Student> findStudentById(String studentId) {
        return students.stream()
            .filter(s -> s.getId().equals(studentId))
            .findFirst();
    }

    public List<Student> listAllStudents() {
        return new ArrayList<>(students);
    }
    
    public boolean updateStudent(Student updatedStudent) {
        Optional<Student> studentOpt = findStudentById(updatedStudent.getId());
        if (studentOpt.isPresent()) {
            students.remove(studentOpt.get());
            students.add(updatedStudent);
            System.out.println("Student " + updatedStudent.getFullName() + " updated successfully.");
            return true;
        }
        System.out.println("Error: Student with ID " + updatedStudent.getId() + " not found.");
        return false;
    }
    
    public boolean deactivateStudent(String studentId) {
        Optional<Student> studentOpt = findStudentById(studentId);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setActive(false);
            System.out.println("Student with ID " + studentId + " deactivated successfully.");
            return true;
        }
        System.out.println("Error: Student with ID " + studentId + " not found for deactivation.");
        return false;
    }
}