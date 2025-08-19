import java.nio.file.*;
import java.time.*;

public class PDFValidator {
    public static void validate(File pdfFile) throws IOException {
        // Create report filename based on input PDF
        String reportName = pdfFile.getName().replace(".pdf", "") + 
                          "_validation_" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + 
                          ".txt";
        
       Path reportPath = Paths.get(System.getProperty("user.dir"), 
                          pdfFile.getName().replace(".pdf", "") + "_report.txt");
        try (PDDocument doc = PDDocument.load(pdfFile)) {
            // Generate validation results
            String reportContent = generateReport(doc); 
            
            // Write to file
            Files.write(reportPath, reportContent.getBytes());
            
            System.out.println("✓ Report generated: " + reportPath.toAbsolutePath());
        }
    }

    private static String generateReport(PDDocument doc) {
        PDDocumentInformation info = doc.getDocumentInformation();
        return String.format(
            "PDF Validation Report\n" +
            "====================\n" +
            "File: %s\n\n" +
            "Metadata:\n" +
            "- Author: %s\n" +
            "- Title: %s\n" +
            "- Creation: %s\n\n" +
            "Signatures: %s",
            doc.getDocument().getDocument().getFile(),
            info.getAuthor() != null ? "✅ " + info.getAuthor() : "❌ Missing",
            info.getTitle() != null ? "✅ " + info.getTitle() : "❌ Missing",
            info.getCreationDate() != null ? info.getCreationDate() : "❌ Missing",
            doc.getSignatureDictionaries().isEmpty() ? "❌ None" : "✅ Valid"
        );
    }
}
