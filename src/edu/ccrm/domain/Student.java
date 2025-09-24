package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private List<String> enrolledCourses;
    private boolean active;

    public Student(String id, String fullName, String email, String regNo) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.enrolledCourses = new ArrayList<>();
        this.active = true;
    }

    @Override
    public void displayDetails() {
        System.out.println("--- Student Profile ---");
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.fullName);
        System.out.println("Email: " + this.email);
        System.out.println("Registration No: " + this.regNo);
        System.out.println("Date Joined: " + this.dateJoined);
        System.out.println("Status: " + (active ? "Active" : "Deactivated"));
        System.out.println("Enrolled Courses: " + (this.enrolledCourses.isEmpty() ? "None" : String.join(", ", this.enrolledCourses)));
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(String courseCode) {
        if (!enrolledCourses.contains(courseCode)) {
            enrolledCourses.add(courseCode);
        }
    }
}