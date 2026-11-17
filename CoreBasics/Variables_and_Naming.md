# Variables and Naming Conventions

## What is a Variable?

A variable is like a labeled box that stores data in memory.

```
||
|  Variable   | = age
|¤
|     20      | (value)
||
```

## Declaring Variables

### Syntax
```java
dataType variableName = value;
```

### Examples
```java
int age = 20;
String name = "Alice";
double price = 19.99;
boolean isActive = true;
```

## Rules for Naming Variables

###  DO
1. **Start with letter, `$`, or `_`**
   ```java
   int age = 20;        // Correct
   String $name = "John"; // Correct
   double _score = 95.5;  // Correct
   ```

2. **Use letters, digits, `$`, `_` after first character**
   ```java
   int student2 = 10;   // Correct
   String user_name = "Alice"; // Correct
   ```

3. **Case-sensitive**
   ```java
   int age = 20;
   int Age = 25;  // Different variable!
   int AGE = 30;  // Different variable!
   ```

4. **No reserved keywords**
   ```java
   int class = 10;  // Error! 'class' is keyword
   int myClass = 10;  // Correct
   ```

###  DON'T
1. **Start with digit**
   ```java
   int 2age = 20;  // Error!
   ```

2. **Use spaces**
   ```java
   int my age = 20;  // Error!
   ```

3. **Use special characters (except $, _)**
   ```java
   int age@ = 20;  // Error!
   ```

## Naming Conventions (Best Practices)

### camelCase (for variables and methods)
```java
int studentAge = 20;
String firstName = "John";
double accountBalance = 1000.50;
```

### PascalCase (for classes)
```java
public class StudentRecord { }
public class BankAccount { }
```

### SCREAMING_SNAKE_CASE (for constants)
```java
final int MAX_AGE = 100;
final double PI = 3.14159;
final String DEFAULT_NAME = "Unknown";
```

## Types of Variables

### 1. Local Variables
Declared inside methods, only accessible there.

```java
public void calculate() {
    int sum = 0;  // Local variable
    sum = 10 + 20;
    System.out.println(sum);
}
// sum cannot be used outside this method
```

### 2. Instance Variables (Fields)
Declared in class, outside methods. Belong to object.

```java
public class Student {
    String name;  // Instance variable
    int age;      // Instance variable
}
```

### 3. Static Variables (Class Variables)
Shared among all objects of a class.

```java
public class Student {
    static int totalStudents = 0;  // Shared by all students
    String name;  // Each student has their own
}
```

## Variable Initialization

### Initialize When Declaring (Good Practice!)
```java
int age = 20;  // Good!
String name = "Alice";  // Good!
```

### Initialize Later
```java
int score;
// ... some code ...
score = 95;  // Works but risky
```

**Warning:** Local variables must be initialized before use!

```java
int age;
System.out.println(age);  // Error! Not initialized
```

## Type Inference (Java 10+)

Use `var` to let Java figure out the type:

```java
var age = 20;           // Java knows it's int
var name = "Alice";     // Java knows it's String
var price = 19.99;      // Java knows it's double

// Must be initialized!
var count;  // Error! Cannot infer type
```

**Note:** Only works for local variables, not instance variables

## Multiple Variables in One Line

```java
// Good for related variables
int x = 10, y = 20, z = 30;

String firstName = "John", lastName = "Doe";
```

**Tip:** Don't overuse this - it reduces readability

## Constants (Final Variables)

Use `final` keyword to make constants (values that can't change):

```java
final int MAX_SIZE = 100;
final double PI = 3.14159;
final String APP_NAME = "MyApp";

MAX_SIZE = 200;  // Error! Cannot change final variable
```

**Convention:** Use SCREAMING_SNAKE_CASE for constants

## Variable Scope

### Where can you use a variable?

```java
public class ScopeExample {
    int classVariable = 10;  // Accessible everywhere in class

    public void method() {
        int localVar = 20;   // Only accessible in this method
        System.out.println(classVariable);  // OK
        System.out.println(localVar);       // OK
    }

    public void anotherMethod() {
        System.out.println(classVariable);  // OK
        System.out.println(localVar);       // Error! Not accessible
    }
}
```

## Memory and Variables

```
Primitive types: Store actual value
int age = 20;  // 20 is stored directly

Reference types: Store memory address
String name = "Alice";  // Address of "Alice" is stored
```

## Good Practices

1. **Use meaningful names**
   ```java
   // Bad
   int a = 20;
   
   // Good
   int studentAge = 20;
   ```

2. **Initialize variables when declaring**
   ```java
   // Good
   int count = 0;
   
   // Risky
   int count;
   // ... lots of code ...
   count = 0;
   ```

3. **Use appropriate data types**
   ```java
   // Bad
   long age = 20;
   
   // Good
   int age = 20;
   ```

4. **Don't reuse variable names in same scope**
   ```java
   int age = 20;
   // ... later ...
   int age = 30;  // Confusing and error-prone
   ```

## Common Mistakes

 **Forgetting to initialize**
```java
int age;
System.out.println(age);  // Compile error
```

 **Using wrong data type**
```java
int price = 19.99;  // Error! int can't hold decimals
```

 **Type mismatch**
```java
int score = "95";  // Error! String can't go in int
```

## Key Takeaways

1. Variables store data in memory
2. Must declare with type and name
3. Follow camelCase for variables
4. Initialize when possible
5. Choose appropriate data types
6. Understand variable scope
7. Use `final` for constants

## Practice

Create variables for a student record:
- Name (String)
- Roll number (int)
- Marks (double)
- Grade (char)
- Is passed (boolean)

Print all of them!
