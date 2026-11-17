import java.io.*;
import java.nio.file.*;

public class FileIO {
    public static void main(String[] args) {
        String fileName = "test.txt";

        // Writing to file using java.io
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Hello, this is a test file.");
            writer.newLine();
            writer.write("Learning Java File I/O");
            System.out.println("File written successfully (java.io)");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Reading from file using java.io
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\nFile contents (java.io):");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Modern File I/O using java.nio
        try {
            Files.write(Paths.get("modern.txt"), "Modern Java I/O".getBytes());
            System.out.println("\nFile written successfully (java.nio)");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
