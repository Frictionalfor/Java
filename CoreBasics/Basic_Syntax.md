# Basic Syntax and Code Structure

## The Classic Structure

Every Java program follows this basic pattern:

```java
public class HelloWorld {
    public static void main(String[] args) {
        // Your code goes here
    }
}
```

## Breaking It Down

### `public class HelloWorld`
- `public`: Access modifier (anyone can access this class)
- `class`: Keyword defining a class
- `HelloWorld`: Name of your class (must match filename)

**Rule:** File name must be `HelloWorld.java` for this class

### `public static void main(String[] args)`
This is the **entry point** - where your program starts!

- `public`: Anyone can access it
- `static`: Belongs to class, not an instance
- `void`: Returns nothing
- `main`: Method name (must be exactly "main")
- `String[] args`: Array of string arguments

**Why main is special:** JVM looks for this exact signature to start your program.

## Writing Your First Program

```java
public class MyFirstProgram {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.out.println("My first Java program");
    }
}
```

## Understanding Statements

### `System.out.println()`
- `System`: Built-in class
- `out`: Standard output stream (console)
- `println`: Print line (adds newline at end)

**Difference:**
- `println()` - prints and moves to next line
- `print()` - prints without newline

```java
System.out.print("Hello ");  // No newline
System.out.println("World"); // Newline added
// Output: Hello World
```

## Comments

Comments are notes for humans that Java ignores.

### Single-line Comments
```java
// This is a single-line comment
int age = 20; // Comment at end of line
```

### Multi-line Comments
```java
/*
This is a multi-line comment.
It can span multiple lines.
*/
public class Example {
    // code here
}
```

### Documentation Comments (for advanced users)
```java
/**
 * This method adds two numbers.
 * @param a first number
 * @param b second number
 * @return sum of a and b
 */
public int add(int a, int b) {
    return a + b;
}
```

## Case Sensitivity

Java is **case-sensitive**:
- `hello` ‰ `Hello` ‰ `HELLO`
- Class names must start with uppercase: `HelloWorld`
- Variable names typically start with lowercase: `myVariable`

## Naming Conventions

### Classes
- **PascalCase**: `StudentDatabase`, `BankAccount`
- Start with uppercase letter
- No spaces allowed

### Variables
- **camelCase**: `studentName`, `accountBalance`
- Start with lowercase letter
- Descriptive names

### Constants
- **SCREAMING_SNAKE_CASE**: `MAX_VALUE`, `PI`
- All uppercase with underscores

## Semicolons

Every statement must end with a semicolon `;`

```java
int age = 20;              // Correct
System.out.println("Hi");  // Correct

int x = 5                  // Wrong - missing semicolon
System.out.println(x);
```

## Curly Braces `{}`

Define blocks of code:

```java
public class MyClass {      // Class block starts
    public static void main(String[] args) {  // Method block starts
        System.out.println("Inside main method");
    }                       // Method block ends
}                           // Class block ends
```

## Basic Program Flow

```
1. JVM starts
2. Finds main() method
3. Executes line by line (top to bottom)
4. main() ends
5. Program terminates
```

## Example: Complete Program

```java
// Author: Ferb
// Program: Prints my details

public class MyDetails {
    public static void main(String[] args) {
        System.out.println("Name: John Doe");
        System.out.println("Age: 20");
        System.out.println("City: New York");
        System.out.println("Learning Java!");
    }
}
```

## Common Beginner Mistakes

 **Wrong:** Forgetting semicolon
```java
System.out.println("Hello")  // Missing ;
```

 **Correct:**
```java
System.out.println("Hello");
```

 **Wrong:** Class name doesn't match filename
```java
// File: MyClass.java
public class DifferentName {  // Wrong!
}
```

 **Correct:**
```java
// File: MyClass.java
public class MyClass {  // Correct!
}
```

 **Wrong:** Wrong main method signature
```java
public static void main() {  // Wrong - missing String[] args
}
```

 **Correct:**
```java
public static void main(String[] args) {  // Correct!
}
```

## Key Takeaways

1. Every Java program needs a class with `main()` method
2. `main()` is the entry point - execution starts here
3. Statements end with semicolons `;`
4. Java is case-sensitive
5. Use comments to explain your code
6. Follow naming conventions for readability

## Practice

Try writing a program that prints:
```
My name is [Your Name]
I am learning Java
Java is fun!
```

## Next Topic

Now that you know the structure, let's learn about **Primitive Data Types** - the building blocks of data in Java!
