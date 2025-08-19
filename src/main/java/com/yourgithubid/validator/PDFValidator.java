import java.io.*;
import java.nio.file.*;

public class PDFValidator {
    public static void validate(File pdfFile) {
        try {
            // 1. Get ABSOLUTE output path
            Path reportPath = Paths.get(
                System.getProperty("user.dir"),
                pdfFile.getName().replace(".pdf", "_validation.txt")
            );
            
            // 2. DEBUG: Print critical info
            System.out.println("\n=== DEBUG INFO ===");
            System.out.println("Current dir: " + System.getProperty("user.dir"));
            System.out.println("Attempting to write to: " + reportPath);
            System.out.println("Parent exists? " + Files.exists(reportPath.getParent()));
            
            // 3. Create file with explicit permissions
            String content = "TEST CONTENT";
            Files.write(reportPath, content.getBytes(), 
                StandardOpenOption.CREATE, 
                StandardOpenOption.WRITE);
            
            // 4. Verify
            System.out.println("File exists after write? " + Files.exists(reportPath));
            System.out.println("=== END DEBUG ===");
            
        } catch (Exception e) {
            System.err.println("CRITICAL ERROR:");
            e.printStackTrace();
        }
    }
}
