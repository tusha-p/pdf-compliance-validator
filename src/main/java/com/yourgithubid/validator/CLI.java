package com.yourgithubid.validator;

import java.io.File;

public class CLI {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar validator.jar <PDF_FILE_PATH>");
            return;
        }
        try {
            File pdfFile = new File(args[0]);
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
