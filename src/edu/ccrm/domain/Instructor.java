package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private String department;
    private List<String> assignedCourses;

    public Instructor(String id, String fullName, String email, String department) {
        super(id, fullName, email);
        this.department = department;
        this.assignedCourses = new ArrayList<>();
    }

    @Override
    public void displayDetails() {
        System.out.println("--- Instructor Profile ---");
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.fullName);
        System.out.println("Email: " + this.email);
        System.out.println("Department: " + this.department);
        System.out.println("Date Joined: " + this.dateJoined);
        System.out.println("Assigned Courses: " + (this.assignedCourses.isEmpty() ? "None" : String.join(", ", this.assignedCourses)));
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getAssignedCourses() {
        return assignedCourses;
    }
}