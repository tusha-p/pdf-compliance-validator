package com.yourgithubid.validator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.io.File;
import java.io.IOException;

public class PDFValidator {
    public static void validate(File file) throws IOException {
        System.out.println("\n Validating: " + file.getName());
        try (PDDocument doc = PDDocument.load(file)) {
            PDDocumentInformation info = doc.getDocumentInformation();
            System.out.println("Author: " + (info.getAuthor() != null ? "Y" : "Missing"));
            System.out.println("Modified: " + (info.getModificationDate() != null ? "Y" : "Missing"));
            System.out.println("Signed: " + (!doc.getSignatureDictionaries().isEmpty() ? "Y" : "Not signed"));
        }
    }
}
