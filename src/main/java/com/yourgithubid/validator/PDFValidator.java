package com.yourgithubid.validator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.Loader;
import java.io.File;
import java.io.IOException;

public class PDFValidator {
    
    public static final String FREE_VERSION = "1.0-free";
    
    public static void validate(String filePath) throws IOException {
        // Use Loader instead of PDDocument directly
        try (PDDocument doc = Loader.loadPDF(new File(filePath))) {
            System.out.println("\n📄 PDF Compliance Report (Free v" + FREE_VERSION + ")");
            System.out.println("File: " + filePath);
            
            // ===== FREE TIER CHECKS =====
            PDDocumentInformation info = doc.getDocumentInformation();
            checkFieldExists(info.getAuthor(), "Author");
            checkFieldExists(info.getModificationDate(), "Modification Timestamp");
            checkSignatures(doc);
            
            // ===== PRO TIER PLACEHOLDERS =====
            System.out.println("\n🔒 Pro Features:");
            System.out.println("[PRO] ISO 8601 Validation - Upgrade required");
            System.out.println("[PRO] RBAC Analysis - Upgrade required");
        }
    }
    
    private static void checkFieldExists(Object field, String fieldName) {
        System.out.println(fieldName + ": " + 
            (field != null ? "✅" : "❌ Missing (" + getRelevantCFR(fieldName) + ")"));
    }
    
    private static void checkSignatures(PDDocument doc) throws IOException {
        boolean isSigned = !doc.getSignatureDictionaries().isEmpty();
        System.out.println("Digitally Signed: " + (isSigned ? "✅" : "⚠️ Not signed"));
        
        if (!isSigned) {
            System.out.println("Permissions: " + 
                (doc.getAccessPermission().canModify() ? "⚠️ Editing allowed" : "✅ Read-only"));
        }
    }
    
    private static String getRelevantCFR(String fieldName) {
        return fieldName.contains("Timestamp") ? "§11.10(a)" : "§11.200";
    }
}
