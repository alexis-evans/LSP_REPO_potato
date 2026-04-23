package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class for demonstrating the Template Method report system.
 */
public class ReportDriver {

    /**
     * Runs the report system.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();

        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();
        }
    }
}