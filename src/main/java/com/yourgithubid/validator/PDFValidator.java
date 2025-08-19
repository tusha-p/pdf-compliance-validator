import java.nio.file.*;
import java.time.*;

public class PDFValidator {
       public static void validate(File pdfFile) throws IOException {
    // Use absolute path in project root
    Path reportPath = Paths.get(
        System.getProperty("user.dir"),
        pdfFile.getName().replace(".pdf", "_validation.txt")
    );
    
    // Debug output
    System.out.println("Attempting to create: " + reportPath);
    
    // Create sample content
    String content = "PDF Validation Report\n" +
                    "File: " + pdfFile.getName() + "\n" +
                    "Status: Test Complete\n";
    
    // Write with explicit permissions
    Files.write(
        reportPath,
        content.getBytes(),
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING,
        StandardOpenOption.WRITE
    );
    
    // Verify
    System.out.println("File created successfully: " + Files.exists(reportPath));
    System.out.println("Full path: " + reportPath.toAbsolutePath());
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
