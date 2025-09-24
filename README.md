# Campus Course & Records Manager (CCRM)

### Project Overview
The Campus Course & Records Manager (CCRM) is a comprehensive, console-based Java SE application that streamlines the management of students, courses, enrollments, grades, and academic records for educational institutes. This system provides complete functionality for managing students, courses, enrollments, grades, and academic records with robust file operations and data persistence capabilities.

---

## Key Features
* **Student Management**: Create, update, and manage student profiles and enrollment status.
* **Course Management**: Handle course creation, updates, and instructor assignments.
* **Enrollment System**: Manage student course enrollments with business rule validation.
* **Grading & Transcripts**: Record grades, calculate GPA, and generate academic transcripts.
* **File Operations**: Import/export data via CSV files with backup and archival capabilities.
* **Reporting**: Generate various academic reports using modern Java Stream API.

---

## How to Run

### Prerequisites
* **JDK Version**: Java 11 or higher
* **IDE**: Eclipse IDE for Java Developers (recommended)
* **Operating System**: Windows 10/11 (installation guide provided)

### Running the Application
1.  **Clone the repository**:
    ```bash
    git clone [your-repository-url]
    cd CCRM
    ```
2.  **Compile the project**:
    ```bash
    javac -d bin src/edu/ccrm/**/*.java
    ```
3.  **Run the application**:
    ```bash
    java -cp bin edu.ccrm.cli.Main
    ```
4.  **Enable Assertions** (recommended for testing):
    ```bash
    java -ea -cp bin edu.ccrm.cli.Main
    ```
<br>

***

## Technical Highlights
This project was developed with a strong focus on demonstrating core design patterns and advanced OOP principles. 

### Java Editions Comparison
| Feature | Java ME (Micro Edition) | Java SE (Standard Edition) | Java EE (Enterprise Edition) |
|:---|:---|:---|:---|
| **Target Platform** | Mobile devices, embedded systems | Desktop applications, servers | Enterprise web applications |
| **Application Types** | Mobile apps, IoT devices | Standalone applications, applets | Web services, enterprise apps |
| **Core APIs** | Limited subset of Java APIs | Complete Java API set | Java SE + additional enterprise APIs |
| **Memory Footprint** | Minimal (KBs to few MBs) | Moderate (10s to 100s of MBs) | Large (100s of MBs to GBs) |
| **Key Technologies** | CLDC, MIDP, CDC | Swing, AWT, NIO, Collections | Servlets, JSP, EJB, JPA, CDI |
| **Examples** | Feature phones, smart cards | NetBeans, Eclipse, desktop tools | Banking systems, e-commerce |
| **Current Status** | Legacy (replaced by Android) | Active development | Transferred to Eclipse Foundation |

### Java Architecture: JDK, JRE, JVM
* **Java Virtual Machine (JVM)**: The runtime environment that executes Java bytecode. It provides platform independence, automatic garbage collection, and a secure environment.
* **Java Runtime Environment (JRE)**: Contains everything needed to run Java applications, including the JVM and core libraries.
* **Java Development Kit (JDK)**: The complete development environment, which includes the JRE and all the development tools like the compiler (`javac`) and debugger (`jdb`).

***

## Technical Implementation Mapping
| Syllabus Topic | Implementation Location | Description |
|:---|:---|:---|
| **OOP - Encapsulation** | `edu.ccrm.domain.*` | Private fields with getters/setters in Student, Course classes |
| **OOP - Inheritance** | `edu.ccrm.domain.Person` → `Student`, `Instructor` | Abstract Person class extended by concrete classes |
| **OOP - Abstraction** | `edu.ccrm.domain.Person` | Abstract class with abstract methods `getRole()`, `displayInfo()` |
| **OOP - Polymorphism** | `edu.ccrm.service.*` interfaces | Persistable interface with multiple implementations |
| **Interfaces** | `edu.ccrm.service.Persistable` | Generic interface with default methods |
| **Enums** | `edu.ccrm.domain.Grade`, `Semester` | Enums with constructors and fields |
| **Exception Handling** | `edu.ccrm.exception.*` | Custom exceptions: DuplicateEnrollmentException, MaxCreditLimitExceededException |
| **Collections Framework** | `StudentService`, `CourseService` | HashMap for storage, List for collections |
| **Generics** | `Persistable<T>` interface | Generic interface and method parameters |
| **Lambda Expressions** | `edu.ccrm.util.Comparators` | Student sorting, course filtering predicates |
| **Stream API** | `TranscriptService.generateReports()` | GPA calculations, filtering, data aggregation |
| **Date/Time API** | `edu.ccrm.domain.Student` | LocalDateTime for enrollment dates, timestamps |
| **NIO.2** | `edu.ccrm.io.ImportExportService` | Path, Files APIs for CSV operations |
| **Recursion** | `edu.ccrm.util.RecursiveUtils` | Directory size calculation, file tree traversal |
| **Design Patterns - Singleton** | `edu.ccrm.config.AppConfig` | Thread-safe singleton implementation |
| **Design Patterns - Builder** | `edu.ccrm.domain.Course.Builder` | Builder pattern for Course construction |
| **Nested Classes** | `Course.Builder` (static), `TranscriptGenerator.ReportFormatter` (inner) | Static nested and inner class examples |
| **Anonymous Classes** | `MainMenu.createComparator()` | Anonymous Comparator implementation |
| **Assertions** | Throughout service classes | Invariant checking with assert statements |

***

## Package Structure and Architecture
The project's codebase is organized into several logical packages, each with a distinct responsibility, which helps maintain a clean separation of concerns.

---

### Author
**Author**: Kaushal Tanna<br>
**Reg. No.**: 24BCE10364<br>
**Course**: (CSE2006) Programming in Java
**Institution**: Vellore Institute of Technology, Bhopal
**Date**: 2025-09-24
