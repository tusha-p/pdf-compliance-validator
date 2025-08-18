package com.yourgithubid.validator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import java.io.File;
import java.io.IOException;

public class PDFValidator {
    
    public static final String FREE_VERSION = "1.0-free";
    
    public static void validate(String filePath) throws IOException {
        try (PDDocument doc = PDDocument.load(new File(filePath))) {
            System.out.println("\n📄 PDF Compliance Report (Free v" + FREE_VERSION + ")");
            System.out.println("File: " + filePath);
            
            // Check metadata
            PDDocumentInformation info = doc.getDocumentInformation();
            checkFieldExists(info.getAuthor(), "Author");
            checkFieldExists(info.getModificationDate(), "Modification Timestamp");
            
            // Check signatures
            boolean isSigned = !doc.getSignatureDictionaries().isEmpty();
            System.out.println("Digitally Signed: " + (isSigned ? "✅" : "⚠️ Not signed"));
            
            // Check permissions
            AccessPermission ap = doc.getCurrentAccessPermission();
            System.out.println("Permissions: " + 
                (ap.canModify() ? "⚠️ Editing allowed" : "✅ Read-only"));
            
            // Pro features placeholder
            System.out.println("\n🔒 Pro Features:");
            System.out.println("[PRO] ISO 8601 Validation - Upgrade required");
            System.out.println("[PRO] RBAC Analysis - Upgrade required");
        }
    }
    
    private static void checkFieldExists(Object field, String fieldName) {
        System.out.println(fieldName + ": " + 
            (field != null ? "✅" : "❌ Missing (" + getRelevantCFR(fieldName) + ")"));
    }
    
    private static String getRelevantCFR(String fieldName) {
        return fieldName.contains("Timestamp") ? "§11.10(a)" : "§11.200";
    }
}
