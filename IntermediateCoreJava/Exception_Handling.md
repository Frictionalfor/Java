# Exception Handling

## What is an Exception?

An exception is an **unexpected event** that disrupts normal program flow.

Real-world analogy:
- You're driving (program execution)
- Car runs out of fuel (exception)
- You handle it by stopping at gas station (exception handling)

## Why Handle Exceptions?

Without handling:
```
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at Main.main(Main.java:5)
Program crashes!
```

With handling:
```
Error: Division by zero
Program continues running!
```

## Types of Exceptions

### 1. Checked Exceptions
- **Checked at compile time**
- Must be handled or declared
- Examples: `IOException`, `FileNotFoundException`

### 2. Unchecked Exceptions (Runtime)
- **Checked at runtime**
- Not required to handle
- Examples: `ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException`

### 3. Errors
- Serious problems (JVM crashes, out of memory)
- Not catchable normally
- Examples: `OutOfMemoryError`, `StackOverflowError`

## Exception Hierarchy

```
Throwable (parent)
| Exception
|   | IOException (checked)
|   | SQLException (checked)
|   | RuntimeException (unchecked)
|       | ArithmeticException
|       | NullPointerException
|       | ArrayIndexOutOfBoundsException
| Error
    | OutOfMemoryError
    | StackOverflowError
```

## Exception Handling Keywords

### 1. `try` - Try Block
Code that might throw exception.

```java
try {
    int result = 10 / 0;  // Might throw exception
}
```

### 2. `catch` - Catch Block
Handle the exception.

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero!");
}
```

### 3. `finally` - Finally Block
Always executes (whether exception occurs or not).

```java
try {
    // code
} catch (Exception e) {
    // handle exception
} finally {
    System.out.println("Always executes");
}
```

### 4. `throw` - Throw Exception
Manually throw exception.

```java
if (age < 18) {
    throw new ArithmeticException("Age must be 18+");
}
```

### 5. `throws` - Declare Exception
Declare that method might throw exception.

```java
public void readFile() throws IOException {
    // code that might throw IOException
}
```

## Complete Example

```java
import java.util.Scanner;

public class ExceptionDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter two numbers: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            
            int result = divide(a, b);
            System.out.println("Result: " + result);
            
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero!");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            
        } finally {
            System.out.println("Finally block executed");
            scanner.close();
        }
    }
    
    public static int divide(int a, int b) throws ArithmeticException {
        return a / b;
    }
}
```

## Multiple Catch Blocks

```java
try {
    // Multiple exceptions possible
    int[] arr = {1, 2, 3};
    int result = arr[5] / 0;
    
} catch (ArithmeticException e) {
    System.out.println("Arithmetic error: " + e.getMessage());
    
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Array index error: " + e.getMessage());
    
} catch (Exception e) {
    System.out.println("General error: " + e.getMessage());
}
```

## Multi-Catch (Java 7+)

Handle multiple exceptions in one catch block.

```java
try {
    // code
} catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
    System.out.println("Error: " + e.getMessage());
}
```

## Throw vs Throws

| Throw | Throws |
|-------|--------|
| Used inside method body | Used in method signature |
| Throws one exception | Declares multiple exceptions |
| `throw new Exception()` | `throws Exception1, Exception2` |

```java
// Throw
public void checkAge(int age) {
    if (age < 18) {
        throw new ArithmeticException("Underage");
    }
}

// Throws
public void readFile() throws IOException, FileNotFoundException {
    // code
}
```

## Creating Custom Exceptions

```java
// Custom exception class
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Using custom exception
public class VoteChecker {
    public void checkVotingAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }
        System.out.println("Eligible to vote!");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        VoteChecker checker = new VoteChecker();
        
        try {
            checker.checkVotingAge(16);
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

## Common Built-in Exceptions

| Exception | Reason | Example |
|-----------|--------|---------|
| `ArithmeticException` | Math error | Division by zero |
| `NullPointerException` | Null reference | Calling method on null |
| `ArrayIndexOutOfBoundsException` | Invalid array index | Accessing arr[10] in 5-element array |
| `NumberFormatException` | Invalid number format | `Integer.parseInt("abc")` |
| `IOException` | I/O error | File not found |
| `ClassNotFoundException` | Class not found | Wrong class name |

## Handling Common Exceptions

### NullPointerException
```java
String text = null;

try {
    System.out.println(text.length());
} catch (NullPointerException e) {
    System.out.println("Text is null!");
}
```

### NumberFormatException
```java
try {
    int num = Integer.parseInt("abc");
} catch (NumberFormatException e) {
    System.out.println("Invalid number format!");
}
```

### ArrayIndexOutOfBoundsException
```java
int[] arr = {1, 2, 3};

try {
    System.out.println(arr[5]);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Invalid index!");
}
```

## Best Practices

1. **Catch specific exceptions first, then general**
```java
try {
    // code
} catch (ArithmeticException e) {
    // specific
} catch (Exception e) {
    // general
}
```

2. **Don't swallow exceptions**
```java
// Bad
try {
    // code
} catch (Exception e) {
    // empty - what happened?
}

// Good
try {
    // code
} catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
    e.printStackTrace();
}
```

3. **Use finally for cleanup**
```java
Connection conn = null;
try {
    conn = getConnection();
    // use connection
} catch (Exception e) {
    e.printStackTrace();
} finally {
    if (conn != null) {
        conn.close();  // Always close
    }
}
```

4. **Try-with-resources (Java 7+)**
```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
} catch (IOException e) {
    e.printStackTrace();
}
// Resource automatically closed!
```

## Practical Example: Voting System

```java
import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class VotingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        
        try {
            validateAge(age);
            System.out.println("You can vote!");
            
        } catch (InvalidAgeException e) {
            System.out.println("Cannot vote: " + e.getMessage());
            
        } finally {
            System.out.println("Thank you for using voting system");
            scanner.close();
        }
    }
    
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }
        if (age > 120) {
            throw new InvalidAgeException("Invalid age");
        }
    }
}
```

## Key Takeaways

1. Exceptions are unexpected events
2. `try-catch` handles exceptions
3. `finally` always executes
4. `throw` creates exception, `throws` declares it
5. Create custom exceptions for specific errors
6. Handle exceptions gracefully, don't ignore them
7. Use try-with-resources for auto-close

## Practice

1. Create calculator that handles division by zero
2. Create custom exception for invalid password
3. Read file with exception handling
4. Validate user input with try-catch
