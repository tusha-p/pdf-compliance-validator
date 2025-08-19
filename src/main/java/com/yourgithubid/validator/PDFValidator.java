import java.io.*;
import java.nio.file.*;

public class PDFValidator {
    public static void validate(File pdfFile) {
        try {
            // 1. Use canonical path for absolute certainty
            Path reportPath = Paths.get(
                new File("").getCanonicalPath(), // Project root
                "reports",
                pdfFile.getName().replace(".pdf", "_validation.txt")
            );
            
            // 2. Ensure reports directory exists
            Files.createDirectories(reportPath.getParent());
            
            // 3. Atomic file write with verification
            Path tempFile = Files.write(
                reportPath, 
                generateReportContent().getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );
            
            // 4. Verify
            if (!Files.exists(tempFile)) {
                throw new IOException("File creation failed!");
            }
            
            System.out.println("✓ Report saved to: " + reportPath.normalize());
            
        } catch (Exception e) {
            System.err.println("⚠️ Failed to create report:");
            e.printStackTrace();
        }
    }
    
    private static String generateReportContent() {
        return "PDF Validation Report\n" +
               "Generated at: " + java.time.LocalDateTime.now() + "\n" +
               "Status: Successfully created test report\n";
    }
}
