package com.yourgithubid.validator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.io.File;
import java.io.IOException;

public class PDFValidator {
    
    public static void validate(File pdfFile) throws IOException {
        // Pre-validation checks
        if (pdfFile == null) {
            throw new IllegalArgumentException("PDF file cannot be null");
        }
        
        if (!pdfFile.exists()) {
            throw new IOException("File does not exist: " + pdfFile.getAbsolutePath());
        }

        if (!pdfFile.getName().toLowerCase().endsWith(".pdf")) {
            throw new IOException("Not a PDF file: " + pdfFile.getName());
        }

        System.out.println("\n🔍 Validating: " + pdfFile.getName());
        
        try (PDDocument doc = PDDocument.load(pdfFile)) {
            PDDocumentInformation info = doc.getDocumentInformation();
            
            // Validate required metadata
            validateMetadata(info);
            
            // Validate signatures
            validateSignatures(doc);
        }
    }

    private static void validateMetadata(PDDocumentInformation info) {
        System.out.println("\n📄 Metadata Validation:");
        System.out.println("Author: " + (info.getAuthor() != null ? "✅" : "❌ Missing"));
        System.out.println("Title: " + (info.getTitle() != null ? "✅" : "❌ Missing"));
        System.out.println("Modified: " + (info.getModificationDate() != null ? "✅" : "❌ Missing"));
    }

    private static void validateSignatures(PDDocument doc) throws IOException {
        System.out.println("\n🖋️ Signature Validation:");
        boolean isSigned = !doc.getSignatureDictionaries().isEmpty();
        System.out.println("Document signed: " + (isSigned ? "✅" : "❌ Not signed"));
        
        if (isSigned) {
            System.out.println("Signature count: " + doc.getSignatureDictionaries().size());
        }
    }
}
