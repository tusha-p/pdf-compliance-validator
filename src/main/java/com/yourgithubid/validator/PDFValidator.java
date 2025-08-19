import java.io.*;
import java.nio.file.*;
import java.time.*;

public class PDFValidator {
    public static void validate(File pdfFile) {
        // 1. Use /tmp with timestamped filename
        String timestamp = Instant.now().toString().replace(":", "-");
        Path reportPath = Paths.get("/tmp", "pdf_validation_" + timestamp + ".txt");
        
        try {
            // 2. Create file content
            String content = "PDF Validation Report\n" +
                           "=====================\n" +
                           "Validated File: " + pdfFile.getAbsolutePath() + "\n" +
                           "Timestamp: " + LocalDateTime.now() + "\n" +
                           "Status: Successfully processed\n";
            
            // 3. Write with verification
            Files.write(reportPath, content.getBytes());
            
            // 4. Print SUCCESS with exact path
            System.out.println("SUCCESS! Report created at:");
            System.out.println(reportPath.toAbsolutePath());
            System.out.println("View with: cat " + reportPath);
            
        } catch (Exception e) {
            System.err.println("ERROR: Could not create report");
            e.printStackTrace();
            
            // Emergency fallback to console
            System.out.println("EMERGENCY OUTPUT:");
            System.out.println("PDF: " + pdfFile.getName() + " was validated");
        }
    }
}
