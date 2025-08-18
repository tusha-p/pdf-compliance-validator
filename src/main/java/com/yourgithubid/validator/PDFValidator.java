package com.yourgithubid.validator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.io.File;
import java.io.IOException;

public class PDFValidator {
    
    public static void validate(String filePath) throws IOException {
        try (PDDocument doc = PDDocument.load(new File(filePath))) {
            System.out.println("Checking: " + filePath);
            
            // Check 1: Basic metadata
            PDDocumentInformation info = doc.getDocumentInformation();
            System.out.println("Author: " + (info.getAuthor() != null ? "✅" : "❌ Missing"));
            
            // Check 2: Audit trail (simplified)
            System.out.println("Modification date: " + 
                (info.getModificationDate() != null ? "✅" : "❌ Missing timestamp"));
            
            // Check 3: Security (placeholder)
            System.out.println("Signed: " + 
                (doc.getSignatureDictionaries().isEmpty() ? "❌ No signatures" : "✅"));
        }
    }
}
