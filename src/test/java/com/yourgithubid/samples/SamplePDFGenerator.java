package com.yourgithubid.samples;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.io.IOException;
import java.util.Calendar;

public class SamplePDFGenerator {
    public static void main(String[] args) throws IOException {
        try (PDDocument doc = new PDDocument()) {
            PDDocumentInformation info = new PDDocumentInformation();
            
            // Set metadata
            info.setAuthor("Test User");
            info.setCreationDate(Calendar.getInstance());
            doc.setDocumentInformation(info);
            
            // Save variations
            doc.save("sample_with_metadata.pdf");  // For basic checks
            doc.save("sample_no_metadata.pdf");    // For negative testing
        }
    }
}
