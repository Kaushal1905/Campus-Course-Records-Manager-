package edu.ccrm.domain;

public final class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final String instructorId;
    private final Semester semester;
    private final String department;

    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructorId = builder.instructorId;
        this.semester = builder.semester;
        this.department = builder.department;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getInstructorId() { return instructorId; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", instructorId='" + instructorId + '\'' +
                ", semester=" + semester +
                ", department='" + department + '\'' +
                '}';
    }

    public static class Builder {
        private final String code;
        private final String title;
        private int credits = 3;
        private String instructorId;
        private Semester semester;
        private String department;

        public Builder(String code, String title) {
            this.code = code;
            this.title = title;
        }

        public Builder credits(int val) {
            this.credits = val;
            return this;
        }

        public Builder instructorId(String val) {
            this.instructorId = val;
            return this;
        }

        public Builder semester(Semester val) {
            this.semester = val;
            return this;
        }

        public Builder department(String val) {
            this.department = val;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}