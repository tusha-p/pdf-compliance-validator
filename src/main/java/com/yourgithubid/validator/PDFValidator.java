import java.io.*;
import java.nio.file.*;

public class PDFValidator {
    public static void validate(File pdfFile) {
        try {
            // 1. Get ABSOLUTE path to project root
            String projectRoot = new File("").getAbsolutePath();
            
            // 2. Create reports directory if missing
            Path reportsDir = Paths.get(projectRoot, "reports");
            if (!Files.exists(reportsDir)) {
                Files.createDirectories(reportsDir);
                System.out.println("Created directory: " + reportsDir);
            }

            // 3. Create output file path
            Path reportPath = reportsDir.resolve(
                pdfFile.getName().replace(".pdf", "_validation.txt")
            );

            // 4. WRITE WITH VERIFICATION
            String content = "PDF Validation Report\n" +
                           "File: " + pdfFile.getName() + "\n" +
                           "Generated: " + java.time.LocalDateTime.now() + "\n";
            
            Files.write(reportPath, content.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
            
            // 5. CONFIRM FILE EXISTS
            if (Files.exists(reportPath)) {
                System.out.println("SUCCESS! Report saved to:");
                System.out.println(reportPath.toAbsolutePath());
            } else {
                throw new IOException("File creation failed silently!");
            }

        } catch (Exception e) {
            System.err.println("CRITICAL ERROR:");
            e.printStackTrace();
            System.err.println("Current dir: " + System.getProperty("user.dir"));
        }
    }
}
