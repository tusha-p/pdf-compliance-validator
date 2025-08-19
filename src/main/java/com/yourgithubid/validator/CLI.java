package com.yourgithubid.validator;

import java.io.File;

public class CLI {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar validator.jar <PDF_FILE>");
            return;
        }
        try {
            // Convert String path to File object
            PDFValidator.validate(new File(args[0]));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
