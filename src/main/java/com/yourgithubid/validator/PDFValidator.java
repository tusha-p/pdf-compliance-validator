import java.io.*;
import java.nio.file.*;

public class PDFValidator {
    public static void validate(File pdfFile) {
        try {
            // 1. Get ABSOLUTE project root
            String projectRoot = new File("").getCanonicalPath();
            System.out.println("Project root: " + projectRoot);

            // 2. Create reports directory with verification
            Path reportsDir = Paths.get(projectRoot, "reports");
            System.out.println("Attempting to create: " + reportsDir);
            
            Files.createDirectories(reportsDir);
            if (!Files.exists(reportsDir)) {
                throw new IOException("Directory creation failed!");
            }
            System.out.println("Directory created successfully");

            // 3. Create file with verification
            Path reportPath = reportsDir.resolve(
                pdfFile.getName().replace(".pdf", "_validation.txt")
            );
            
            String content = "PDF Validation Report\n" +
                          "File: " + pdfFile.getAbsolutePath() + "\n" +
                          "Generated: " + java.time.LocalDateTime.now() + "\n";
            
            Files.write(reportPath, content.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);

            // 4. Verify file creation
            if (!Files.exists(reportPath)) {
                throw new IOException("File creation failed silently!");
            }
            System.out.println("SUCCESS! Report saved to:\n" + 
                reportPath.toAbsolutePath());

        } catch (Exception e) {
            System.err.println("CRITICAL ERROR:");
            e.printStackTrace();
            System.err.println("Current dir: " + System.getProperty("user.dir"));
            
            // Emergency fallback - write to /tmp
            try {
                Path fallbackPath = Paths.get("/tmp/fallback_report.txt");
                Files.write(fallbackPath, "Emergency report".getBytes());
                System.err.println("Wrote fallback to: " + fallbackPath);
            } catch (Exception ee) {
                System.err.println("COULD NOT WRITE ANYWHERE!");
            }
        }
    }
}
