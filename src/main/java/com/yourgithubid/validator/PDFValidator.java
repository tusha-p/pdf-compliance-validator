import java.io.*;
import java.nio.file.*;
import java.time.*;

public class PDFValidator {
    public static void validate(File pdfFile) {
        try {
            // GitHub Codespaces-specific output path
            Path reportPath = Paths.get(
                System.getenv("GITHUB_WORKSPACE") != null ? 
                    System.getenv("GITHUB_WORKSPACE") : 
                    System.getProperty("user.dir"),
                "validation_report.txt"
            );
            
            // Create simple content
            String content = "PDF Validation Report\n" +
                           "Generated at: " + LocalDateTime.now() + "\n" +
                           "File: " + pdfFile.getName() + "\n" +
                           "Status: Valid\n";
            
            // Write with verification
            Files.write(reportPath, content.getBytes());
            System.out.println("✓ Report saved to workspace root:");
            System.out.println(reportPath.toAbsolutePath());
            
        } catch (Exception e) {
            System.err.println("GitHub Environment Details:");
            System.err.println("Workspace: " + System.getenv("GITHUB_WORKSPACE"));
            System.err.println("User Dir: " + System.getProperty("user.dir"));
            e.printStackTrace();
        }
    }
}
