package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

/**
 * ETL (Extract, Transform, Load) Pipeline for processing product data from CSV files.
 * Reads product data from an input CSV file, applies business transformations,
 * and writes the transformed data to an output CSV file.
 */
public class ETLPipeline {
    private final ProductCsvCodec codec;
    private final ProductTransformer transformer;

    /**
     * Constructs an ETLPipeline with the specified codec and transformer.
     *
     * @param codec the CSV codec for reading and writing product data
     * @param transformer the transformer for applying business rules to products
     */
    public ETLPipeline(ProductCsvCodec codec, ProductTransformer transformer) {
        this.codec = codec;
        this.transformer = transformer;
    }

    /**
     * Executes the ETL pipeline to process products from input to output CSV files.
     * The pipeline performs the following steps:
     *   - Writes the output header (ProductID, Name, Price, Category, PriceRange)
     *   - Skips the input file header row if present
     *   - Reads each non-header line from the input file
     *   - Parses the line into a Product (skips invalid rows)
     *   - Transforms the product according to business rules
     *   - Writes the transformed product to the output file
     *
     *
     * @param inputPath the path to the input CSV file (e.g., "data/products.csv")
     * @param outputPath the path to the output CSV file (e.g., "data/transformed_products.csv")
     * @return a Result object containing statistics about the pipeline execution
     * @throws FileNotFoundException if the input file does not exist or cannot be read
     */
    public Result run(String inputPath, String outputPath) throws FileNotFoundException {
        // Track run-summary metrics required by the assignment.
        int nonHeaderRowsEncountered = 0;
        int skippedRows = 0;
        int processedRows = 0;

        try (Scanner scanner = new Scanner(new File(inputPath));
                PrintWriter writer = new PrintWriter(new File(outputPath))) {

            // Always write the canonical output header, even for empty input files.
            codec.writeHeader(writer);

            if (scanner.hasNextLine()) {
                // Consume and ignore the input header row.
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                nonHeaderRowsEncountered++;

                // Parse/validate the row; invalid rows are skipped but still counted.
                Optional<Product> parsedProduct = codec.parse(line);

                if (parsedProduct.isEmpty()) {
                    skippedRows++;
                    continue;
                }

                // Apply business rules and emit one transformed output row.
                Product transformedProduct = transformer.transform(parsedProduct.get());
                codec.writeProduct(writer, transformedProduct);
                processedRows++;
            }
        }

        return new Result(nonHeaderRowsEncountered, processedRows, skippedRows);
    }

    /**
     * Immutable result object containing statistics from an ETL pipeline execution.
     * Provides counts of rows encountered, successfully processed, and skipped during the pipeline run.
     */
    public static class Result {
        private final int nonHeaderRowsEncountered;
        private final int processedRows;
        private final int skippedRows;

        /**
         * Constructs a Result with the specified statistics.
         *
         * @param nonHeaderRowsEncountered the total number of non-header rows encountered in the input
         * @param processedRows the number of rows successfully transformed and written to output
         * @param skippedRows the number of rows skipped due to validation or parsing errors
         */
        public Result(int nonHeaderRowsEncountered, int processedRows, int skippedRows) {
            this.nonHeaderRowsEncountered = nonHeaderRowsEncountered;
            this.processedRows = processedRows;
            this.skippedRows = skippedRows;
        }

        /**
         * Gets the total number of non-header rows encountered in the input file.
         *
         * @return the count of non-header rows read from the input
         */
        public int getNonHeaderRowsEncountered() {
            return nonHeaderRowsEncountered;
        }

        /**
         * Gets the number of rows successfully processed and written to the output file.
         *
         * @return the count of successfully transformed rows
         */
        public int getProcessedRows() {
            return processedRows;
        }

        /**
         * Gets the number of rows skipped due to validation or parsing errors.
         *
         * @return the count of skipped rows
         */
        public int getSkippedRows() {
            return skippedRows;
        }
    }

    /**
     * Main entry point for the ETL pipeline application.
     * Reads products from data/products.csv, transforms them according to business rules,
     * and writes the results to data/transformed_products.csv.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Default paths expected by the assignment specification.
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        // Wire together ETL collaborators and execute once.
        ETLPipeline pipeline = new ETLPipeline(
                new ProductCsvCodec(),
                new ProductTransformer());

        try {
            Result result = pipeline.run(inputPath, outputPath);

            System.out.println("ETL Process Complete!");
            System.out.println("Non-header rows encountered: " + result.getNonHeaderRowsEncountered());
            System.out.println("Transformed rows: " + result.getProcessedRows());
            System.out.println("Skipped rows: " + result.getSkippedRows());
            System.out.println("Output path: " + outputPath);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Ensure the 'data' folder and 'products.csv' exist.");
        }
    }
}
