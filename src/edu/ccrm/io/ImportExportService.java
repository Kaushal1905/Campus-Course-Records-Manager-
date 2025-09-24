package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import edu.ccrm.Service.CourseService;
import edu.ccrm.Service.StudentService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ImportExportService {

    public void importStudents(String filePath, StudentService studentService) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            System.out.println("File not found: " + filePath);
            return;
        }
        
        Files.lines(path).skip(1) // Assuming a header row
            .map(line -> line.split(","))
            .filter(parts -> parts.length == 4)
            .map(parts -> new Student(parts[0], parts[1], parts[2], parts[3]))
            .forEach(studentService::addStudent);
    }
    
    public void exportStudents(String filePath, StudentService studentService) throws IOException {
        String content = studentService.listAllStudents().stream()
            .map(s -> String.join(",", s.getId(), s.getFullName(), s.getEmail(), s.getRegNo()))
            .collect(Collectors.joining("\n"));
        
        Files.writeString(Paths.get(filePath), "ID,FullName,Email,RegNo\n" + content);
        System.out.println("Students exported to " + filePath);
    }

    public void exportCourses(String filePath, CourseService courseService) throws IOException {
        String content = courseService.listAllCourses().stream()
            .map(c -> String.join(",", c.getCode(), c.getTitle(), String.valueOf(c.getCredits()), c.getDepartment(), String.valueOf(c.getSemester())))
            .collect(Collectors.joining("\n"));
        
        Files.writeString(Paths.get(filePath), "Code,Title,Credits,Department,Semester\n" + content);
        System.out.println("Courses exported to " + filePath);
    }
}