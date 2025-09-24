# CCRM Usage Guide

This guide provides step-by-step instructions for using the Campus Course & Records Manager (CCRM) application from the command line.

---

## Main Menu
Upon running the application, you will be presented with the main menu. To select an option, type the corresponding number and press `Enter`.

## 1. Manage Students

This menu allows you to add and view student records.

* **1. Add Student**:
    * Enter the student's ID, full name, email, and registration number when prompted.
    * The system will confirm if the student was added successfully.

* **2. List All Students**:
    * Displays the full profile details for every student in the system, including their ID, name, email, registration number, and enrolled courses.

---

## 2. Manage Courses

This menu handles the creation and listing of course records.

* **1. Add Course**:
    * Enter the course code, title, credits, department, and semester (e.g., SPRING, SUMMER, FALL).
    * The system uses the Builder pattern to create the Course object and confirms successful addition.

* **2. List All Courses**:
    * Displays a list of all registered courses with their details.

---

## 3. Manage Enrollment & Grades

This is where you can enroll students in courses, record grades, and compute GPAs.

* **1. Enroll Student in a Course**:
    * Enter the student ID and the course code.
    * The system will validate the enrollment against the student's credit limit and check for duplicate enrollments.
    * Custom exceptions (`MaxCreditLimitExceededException` and `DuplicateEnrollmentException`) will be thrown and handled if a rule is violated.

* **2. Record Grade for a Student**:
    * Enter the student ID and the course code.
    * Enter a grade from the valid enum values (S, A, B, C, D, E, F). The grade must be a valid enum string (case-insensitive).

* **3. Compute Student GPA**:
    * Enter the student ID.
    * The system will calculate the GPA based on the recorded grades and display it formatted to two decimal places.

---

## 4. Import/Export Data

This section allows you to persist data to and from CSV files. **Before using this feature, ensure you have a `data` folder in your project's root directory.**

* **1. Export Students**:
    * Saves all current student records to `data/students.csv`.

* **2. Export Courses**:
    * Saves all current course records to `data/courses.csv`.

* **3. Import Students (from students.csv)**:
    * Loads student records from the `data/students.csv` file. This is useful for pre-populating the system.

---

## 5. Backup Data

This feature provides a robust way to save a snapshot of your data.

* **1. Create a Backup**:
    * Creates a new timestamped folder (e.g., `backup_20250924_103045`) in the project's root directory.
    * Copies all files from the `data` directory into this new backup folder.

* **2. Calculate Backup Size**:
    * Prompts you to enter the path to a backup folder.
    * Calculates and displays the total size of all files within that folder, demonstrating the use of recursive file traversal.
