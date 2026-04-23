package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class for reports using the Template Method pattern.
 */
public abstract class Report {

    /**
     * Template method that defines the fixed workflow for generating a report.
     */
    public final void generateReport() {
        loadData();

        System.out.println("=== HEADER ===");
        formatHeader();
        System.out.println();

        System.out.println("=== BODY ===");
        formatBody();
        System.out.println();

        System.out.println("=== FOOTER ===");
        formatFooter();
        System.out.println();
    }

    /**
     * Loads the report-specific data.
     */
    protected abstract void loadData();

    /**
     * Formats the report-specific header.
     */
    protected abstract void formatHeader();

    /**
     * Formats the report-specific body.
     */
    protected abstract void formatBody();

    /**
     * Formats the report-specific footer.
     */
    protected abstract void formatFooter();
}