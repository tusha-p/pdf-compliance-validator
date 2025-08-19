package com.yourgithubid.validator;

public class CLI {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar validator.jar <PDF_FILE>");
            return;
        }
        try {
            PDFValidator.validate(new java.io.File(args[0]));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
