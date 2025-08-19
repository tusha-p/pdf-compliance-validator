package com.yourgithubid.validator;

import java.io.File;

public class CLI {
    public static void main(String[] args) {
         System.out.println("Current working directory: " + System.getProperty("user.dir"));
        if (args.length == 0) {
            System.out.println("Usage: java -jar validator.jar <PDF_FILE_PATH>");
            return;
        }
        
        // CORRECT CONVERSION - String to File
        File pdfFile = new File(args[0]); // This is line 10
        
        try {
            if (!pdfFile.exists()) {
                System.out.println("❌ File not found: " + args[0]);
                return;
            }
            PDFValidator.validate(pdfFile);
        } catch (Exception e) {
            System.out.println("❌ Validation failed:");
            e.printStackTrace();
        }
    }
}
