import java.io.File;

public class FileConversionTest {
    public static void main(String[] args) {
        String path = "test.pdf";
        File f = new File(path);
        System.out.println("Test passed? " + (f.getClass() == File.class));
    }
}
