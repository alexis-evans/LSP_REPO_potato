package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class ETLPipeline {
    private final ProductCsvCodec codec;
    private final ProductTransformer transformer;

    public ETLPipeline(ProductCsvCodec codec, ProductTransformer transformer) {
        this.codec = codec;
        this.transformer = transformer;
    }

    public Result run(String inputPath, String outputPath) throws FileNotFoundException {
        int nonHeaderRowsEncountered = 0;
        int skippedRows = 0;
        int processedRows = 0;

        try (Scanner scanner = new Scanner(new File(inputPath));
                PrintWriter writer = new PrintWriter(new File(outputPath))) {

            codec.writeHeader(writer);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                nonHeaderRowsEncountered++;
                Optional<Product> parsedProduct = codec.parse(line);

                if (parsedProduct.isEmpty()) {
                    skippedRows++;
                    continue;
                }

                Product transformedProduct = transformer.transform(parsedProduct.get());
                codec.writeProduct(writer, transformedProduct);
                processedRows++;
            }
        }

        return new Result(nonHeaderRowsEncountered, processedRows, skippedRows);
    }

    public static class Result {
        private final int nonHeaderRowsEncountered;
        private final int processedRows;
        private final int skippedRows;

        public Result(int nonHeaderRowsEncountered, int processedRows, int skippedRows) {
            this.nonHeaderRowsEncountered = nonHeaderRowsEncountered;
            this.processedRows = processedRows;
            this.skippedRows = skippedRows;
        }

        public int getNonHeaderRowsEncountered() {
            return nonHeaderRowsEncountered;
        }

        public int getProcessedRows() {
            return processedRows;
        }

        public int getSkippedRows() {
            return skippedRows;
        }
    }

    public static void main(String[] args) {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

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
