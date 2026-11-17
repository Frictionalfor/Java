# File I/O

## What is File I/O?

File I/O (Input/Output) lets your program **read from and write to files** on disk.

Like reading a book (input) and writing notes in it (output).

## Streams Overview

- **InputStream**: Read bytes from source
- **OutputStream**: Write bytes to destination
- **Reader**: Read characters (text)
- **Writer**: Write characters (text)

## Reading Files

### Using BufferedReader (Classic Way)
```java
import java.io.*;

public class ReadFileDemo {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Using Scanner (Simpler)
```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("file.txt"));
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
```

## Writing Files

### Using BufferedWriter
```java
import java.io.*;

public class WriteFileDemo {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write("Hello, World!");
            writer.newLine();
            writer.write("Learning Java File I/O");
            System.out.println("File written successfully");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

## java.nio (Modern Way - Java 7+)

More efficient and feature-rich.

### Reading with Files
```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NioReadDemo {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("file.txt");
            List<String> lines = Files.readAllLines(path);
            
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Writing with Files
```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NioWriteDemo {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("output.txt");
            Files.write(path, "Hello, NIO!\nLearning Java".getBytes());
            System.out.println("File written");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

## Common File Operations

### Check if File Exists
```java
import java.nio.file.*;

Path path = Paths.get("file.txt");
boolean exists = Files.exists(path);
System.out.println("Exists: " + exists);
```

### Create Directory
```java
Path dir = Paths.get("myfolder");
Files.createDirectory(dir);
```

### Delete File
```java
Path path = Paths.get("file.txt");
Files.delete(path);
```

### Copy File
```java
Path source = Paths.get("source.txt");
Path target = Paths.get("target.txt");
Files.copy(source, target);
```

### Move File
```java
Path source = Paths.get("old.txt");
Path target = Paths.get("new.txt");
Files.move(source, target);
```

## File Information

```java
import java.io.File;

File file = new File("file.txt");

if (file.exists()) {
    System.out.println("Name: " + file.getName());
    System.out.println("Path: " + file.getAbsolutePath());
    System.out.println("Size: " + file.length() + " bytes");
    System.out.println("Readable: " + file.canRead());
    System.out.println("Writable: " + file.canWrite());
}
```

## Complete Example: Copy File

```java
import java.io.*;
import java.nio.file.*;

public class CopyFile {
    public static void main(String[] args) {
        Path source = Paths.get("source.txt");
        Path target = Paths.get("target.txt");
        
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

## Reading and Writing Objects (Serialization)

### Writing Object
```java
import java.io.*;

class Student implements Serializable {
    String name;
    int rollNumber;
    
    Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }
}

public class SerializeDemo {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("student.ser"))) {
            
            Student s = new Student("Alice", 101);
            oos.writeObject(s);
            System.out.println("Student saved");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Reading Object
```java
public class DeserializeDemo {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream("student.ser"))) {
            
            Student s = (Student) ois.readObject();
            System.out.println("Name: " + s.name);
            System.out.println("Roll: " + s.rollNumber);
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

## try-with-resources

Automatically closes resources (Java 7+).

```java
// Traditional way
BufferedReader reader = null;
try {
    reader = new BufferedReader(new FileReader("file.txt"));
    String line = reader.readLine();
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (reader != null) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// try-with-resources (auto-close!)
try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
    String line = reader.readLine();
} catch (IOException e) {
    e.printStackTrace();
}
```

## Character Encoding

```java
// Specify encoding
BufferedWriter writer = new BufferedWriter(
    new OutputStreamWriter(
        new FileOutputStream("file.txt"), 
        "UTF-8"
    )
);
```

## Key Takeaways

1. **File I/O**: Read/write data to/from files
2. **BufferedReader/Writer**: Efficient text reading/writing
3. **Scanner**: Simple text reading
4. **java.nio**: Modern, faster file operations
5. **try-with-resources**: Auto-close resources
6. Handle exceptions properly

## Practice

1. Create file, write 5 lines of text
2. Read file and print all lines
3. Copy content from one file to another
4. Count number of lines in file
5. Read CSV file and process data
