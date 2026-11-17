# Core Basics Notes

## JDK, JRE, and JVM

- **JDK (Java Development Kit)**: Complete package for Java development. Includes JRE + development tools (javac, java, javadoc).
- **JRE (Java Runtime Environment)**: Provides libraries, JVM, and other components to run Java applications.
- **JVM (Java Virtual Machine)**: Executes Java bytecode. Platform-dependent but Java bytecode is platform-independent.

## IDE Setup

Popular IDEs for Java:
- **IntelliJ IDEA**: Most popular, powerful, excellent refactoring
- **Eclipse**: Open source, plugin-rich
- **VS Code**: Lightweight, growing Java support

## Basic Syntax and Code Structure

```java
public class Main {
    public static void main(String[] args) {
        // Code here
    }
}
```

## Primitive Data Types

| Type | Size | Range |
|------|------|-------|
| byte | 1 byte | -128 to 127 |
| short | 2 bytes | -32,768 to 32,767 |
| int | 4 bytes | -2^31 to 2^31-1 |
| long | 8 bytes | -2^63 to 2^63-1 |
| float | 4 bytes | 6-7 decimal digits |
| double | 8 bytes | 15 decimal digits |
| char | 2 bytes | 0 to 65,535 (Unicode) |
| boolean | 1 bit | true or false |

## Variables and Naming Conventions

- Variables store data values
- camelCase for variables: `studentName`
- PascalCase for classes: `Student`
- Constants use SCREAMING_SNAKE_CASE: `MAX_VALUE`

## Operators

- **Arithmetic**: `+`, `-`, `*`, `/`, `%`, `++`, `--`
- **Relational**: `==`, `!=`, `>`, `<`, `>=`, `<=`
- **Logical**: `&&`, `||`, `!`
- **Assignment**: `=`, `+=`, `-=`, `*=`, `/=`

## Conditional Statements

```java
if (condition) {
    // code
} else if (condition) {
    // code
} else {
    // code
}

switch (variable) {
    case value1:
        // code
        break;
    case value2:
        // code
        break;
    default:
        // code
}
```

## Loops

- **for loop**: Fixed number of iterations
- **while loop**: Condition-based iteration
- **do-while loop**: Executes at least once
- **enhanced for loop**: For arrays/collections
