package edu.ccrm.Service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService {
    private final List<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<>();
    }

    public boolean addCourse(Course course) {
        if (courses.stream().anyMatch(c -> c.getCode().equals(course.getCode()))) {
            System.out.println("Error: A course with the code " + course.getCode() + " already exists.");
            return false;
        }
        courses.add(course);
        System.out.println("Course '" + course.getTitle() + "' added successfully.");
        return true;
    }

    public Optional<Course> findCourseByCode(String courseCode) {
        return courses.stream()
            .filter(c -> c.getCode().equals(courseCode))
            .findFirst();
    }

    public List<Course> listAllCourses() {
        return new ArrayList<>(courses);
    }
    
    public boolean updateCourse(Course updatedCourse) {
        Optional<Course> existingCourseOpt = findCourseByCode(updatedCourse.getCode());
        if (existingCourseOpt.isPresent()) {
            courses.remove(existingCourseOpt.get());
            courses.add(updatedCourse);
            System.out.println("Course '" + updatedCourse.getTitle() + "' updated successfully.");
            return true;
        }
        System.out.println("Error: Course with code " + updatedCourse.getCode() + " not found.");
        return false;
    }

    public List<Course> findCoursesByDepartment(String department) {
        return courses.stream()
            .filter(course -> department.equalsIgnoreCase(course.getDepartment()))
            .collect(Collectors.toList());
    }

    public List<Course> findCoursesBySemester(Semester semester) {
        return courses.stream()
            .filter(course -> semester.equals(course.getSemester()))
            .collect(Collectors.toList());
    }
}