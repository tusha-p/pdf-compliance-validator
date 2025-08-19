package com.yourgithubid.validator;

import java.io.File;

public class CLI {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar validator.jar <PDF_FILE_PATH>");
            return;
        }
        
        String filePath = args[0];  // This is the String input
        File pdfFile = new File(filePath);  // Proper conversion to File
        
        try {
            if (!pdfFile.exists()) {
                System.out.println("❌ File not found: " + filePath);
                return;
            }
            PDFValidator.validate(pdfFile);  // Passing File object
        } catch (Exception e) {
            System.out.println("❌ Validation failed:");
            e.printStackTrace();
        }
    }
}
