package org.howard.edu.lsp.finalexam.question2;

/**
 * Represents a course report.
 */
public class CourseReport extends Report {
    private String courseName;
    private int enrollment;

    /**
     * Loads course report data.
     */
    @Override
    protected void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /**
     * Prints the course report header.
     */
    @Override
    protected void formatHeader() {
        System.out.println("Course Report");
    }

    /**
     * Prints the course report body.
     */
    @Override
    protected void formatBody() {
        System.out.println("Course: " + courseName);
        System.out.println("Enrollment: " + enrollment);
    }

    /**
     * Prints the course report footer.
     */
    @Override
    protected void formatFooter() {
        System.out.println("End of Course Report");
    }
}