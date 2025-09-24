package edu.ccrm.Service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EnrollmentService {
    private final Map<String, Map<String, Grade>> studentGrades;
    private final StudentService studentService;
    private final CourseService courseService;
    private static final int MAX_CREDITS_PER_SEMESTER = 15;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentGrades = new HashMap<>();
    }

    public void enrollStudentInCourse(String studentId, String courseCode)
            throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        
        Optional<Student> studentOpt = studentService.findStudentById(studentId);
        if (studentOpt.isEmpty()) {
            throw new IllegalArgumentException("Student not found.");
        }
        Student student = studentOpt.get();

        Optional<Course> courseOpt = courseService.findCourseByCode(courseCode);
        if (courseOpt.isEmpty()) {
            throw new IllegalArgumentException("Course not found.");
        }
        Course course = courseOpt.get();
            
        if (studentGrades.containsKey(studentId) && studentGrades.get(studentId).containsKey(courseCode)) {
            throw new DuplicateEnrollmentException("Student " + student.getFullName() + " is already enrolled in " + course.getTitle());
        }

        int currentCredits = student.getEnrolledCourses().stream()
            .mapToInt(code -> courseService.findCourseByCode(code).map(Course::getCredits).orElse(0))
            .sum();
            
        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new MaxCreditLimitExceededException("Cannot enroll. Max credit limit of " + MAX_CREDITS_PER_SEMESTER + " exceeded.");
        }

        student.enrollInCourse(courseCode);
        studentGrades.computeIfAbsent(studentId, k -> new HashMap<>()).put(courseCode, null);
        
        System.out.println("Student " + student.getFullName() + " enrolled in " + course.getTitle() + ".");
    }

    public void recordGrade(String studentId, String courseCode, Grade grade) {
        if (studentGrades.containsKey(studentId) && studentGrades.get(studentId).containsKey(courseCode)) {
            studentGrades.get(studentId).put(courseCode, grade);
            System.out.println("Grade " + grade + " recorded for student " + studentId + " in course " + courseCode);
        } else {
            System.out.println("Error: Student not enrolled in this course or course not found.");
        }
    }

    public double computeGPA(String studentId) {
        Map<String, Grade> grades = studentGrades.get(studentId);
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        double totalGradePoints = 0.0;
        int totalCredits = 0;

        for (Map.Entry<String, Grade> entry : grades.entrySet()) {
            String courseCode = entry.getKey();
            Grade grade = entry.getValue();

            if (grade != null) {
                int credits = courseService.findCourseByCode(courseCode)
                    .map(Course::getCredits)
                    .orElse(0);

                totalGradePoints += grade.getGradePoints() * credits;
                totalCredits += credits;
            }
        }
        return totalCredits == 0 ? 0.0 : totalGradePoints / totalCredits;
    }
}