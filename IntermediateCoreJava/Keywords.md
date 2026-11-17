# Keywords: this, super, static, final

## What are Keywords?

Keywords are reserved words with special meaning in Java. You cannot use them as variable names!

Examples: `public`, `class`, `static`, `void`, `int`, `if`, `else`, etc.

## The `this` Keyword

`this` refers to the **current object**. It's like saying "me" or "myself".

### When to Use `this`

#### 1. Distinguish Between Variables
```java
public class Student {
    String name;
    
    public Student(String name) {
        this.name = name;  // 'this.name' = instance variable
                           // 'name' = parameter
    }
}
```

**Without `this`:**
```java
public Student(String name) {
    name = name;  // Confusing! Which is which?
}
```

#### 2. Pass Current Object as Parameter
```java
public class Student {
    String name;
    
    public void printDetails() {
        display(this);  // Pass current object
    }
    
    public void display(Student s) {
        System.out.println(s.name);
    }
}
```

#### 3. Call Another Constructor (Constructor Chaining)
```java
public class Student {
    String name;
    int age;
    String grade;
    
    // Constructor 1
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Constructor 2 - calls Constructor 1
    public Student(String name, int age, String grade) {
        this(name, age);  // Calls first constructor
        this.grade = grade;
    }
}
```

## The `super` Keyword

`super` refers to the **parent class** (superclass). Like saying "my parent".

### When to Use `super`

#### 1. Access Parent Class Methods
```java
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    void sound() {
        super.sound();  // Call parent's sound()
        System.out.println("Dog barks");
    }
}

Dog dog = new Dog();
dog.sound();
// Output:
// Animal makes sound
// Dog barks
```

#### 2. Access Parent Class Constructor
```java
class Person {
    String name;
    
    Person(String name) {
        this.name = name;
    }
}

class Student extends Person {
    int rollNumber;
    
    Student(String name, int rollNumber) {
        super(name);  // Call parent constructor
        this.rollNumber = rollNumber;
    }
}
```

**Important:** `super()` must be the FIRST statement in constructor!

#### 3. Access Parent Class Variables
```java
class Person {
    String name = "Unknown";
}

class Student extends Person {
    String name = "Alice";
    
    void display() {
        System.out.println(super.name);  // Unknown (parent)
        System.out.println(this.name);   // Alice (current)
        System.out.println(name);        // Alice (current)
    }
}
```

## The `static` Keyword

`static` means **belongs to the class, not individual objects**.

### Static Variables (Class Variables)

Shared among all objects of the class.

```java
public class Student {
    String name;           // Instance variable - each student has own
    static int totalStudents = 0;  // Static - shared by all
}

// Create students
Student s1 = new Student();
Student s2 = new Student();

s1.name = "Alice";
s2.name = "Bob";

Student.totalStudents = 2;  // Shared count
```

### Static Methods

Can be called without creating object.

```java
public class MathUtils {
    public static int add(int a, int b) {
        return a + b;
    }
}

// Call without object
int sum = MathUtils.add(5, 3);
```

### Static Block

Runs once when class is first loaded.

```java
public class Database {
    static {
        // Initialize database connection
        System.out.println("Loading database driver...");
    }
}
```

### When to Use `static`

**Use for:**
- Constants: `final static`
- Utility methods: `Math.sqrt()`
- Counter variables shared by all objects

**Don't use for:**
- Instance-specific data

```java
public class Counter {
    static int count = 0;  // Shared
    
    Counter() {
        count++;
    }
}

Counter c1 = new Counter();  // count = 1
Counter c2 = new Counter();  // count = 2
Counter c3 = new Constructor();  // count = 3
System.out.println(Counter.count);  // 3
```

### Important: Static Context Rules

 **Cannot use `this` in static context**
```java
public static void printName() {
    System.out.println(this.name);  // Error! No 'this' in static
}
```

 **Cannot access non-static from static directly**
```java
public class Test {
    String name = "Alice";
    
    public static void printName() {
        System.out.println(name);  // Error! Non-static in static
    }
}
```

 **Correct:** Create object first
```java
public static void printName() {
    Test obj = new Test();
    System.out.println(obj.name);  // OK
}
```

## The `final` Keyword

`final` means **cannot be changed**. Like a permanent marker.

### Final Variables (Constants)

```java
public class Constants {
    final double PI = 3.14159;
    final int MAX_SIZE = 100;
    final String APP_NAME = "MyApp";
    
    public void print() {
        // PI = 3.14;  // Error! Cannot change final variable
    }
}
```

**Convention:** Use SCREAMING_SNAKE_CASE for constants

### Final Methods

Cannot be overridden in subclasses.

```java
public class Parent {
    public final void display() {
        System.out.println("Parent display");
    }
}

public class Child extends Parent {
    // public void display() {  // Error! Cannot override final method
    //     System.out.println("Child display");
    // }
}
```

### Final Classes

Cannot be extended (no subclasses).

```java
public final class MathUtils {
    // Utility methods
}

// class AdvancedMath extends MathUtils {  // Error! Final class
// }
```

**Use case:** Security (String class is final)

## Combining Keywords

### static final (Constants)
```java
public class MathConstants {
    public static final double PI = 3.14159;
    public static final int MAX_VALUE = Integer.MAX_VALUE;
}

// Access without object
double pi = MathConstants.PI;
```

### Common Pattern: Static Utility Classes
```java
public class StringUtils {
    private StringUtils() {}  // Private constructor - prevent instantiation
    
    public static boolean isPalindrome(String str) {
        // utility method
    }
    
    public static String reverse(String str) {
        // utility method
    }
}
```

## Practical Examples

### Example 1: Counter with Static
```java
public class Counter {
    private int instanceCount = 0;
    private static int totalCount = 0;
    
    public Counter() {
        instanceCount++;
        totalCount++;
    }
    
    public int getInstanceCount() {
        return instanceCount;
    }
    
    public static int getTotalCount() {
        return totalCount;
    }
}

// Usage
Counter c1 = new Counter();
Counter c2 = new Counter();

System.out.println(c1.getInstanceCount());  // 1
System.out.println(c2.getInstanceCount());  // 1
System.out.println(Counter.getTotalCount());  // 2
```

### Example 2: final for Security
```java
public class BankAccount {
    private final String accountNumber;  // Cannot change
    private double balance;
    
    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    // No setter for accountNumber - it's final and set in constructor
    public String getAccountNumber() {
        return accountNumber;
    }
}
```

## Key Takeaways

1. `this`: Current object reference
2. `super`: Parent class reference
3. `static`: Belongs to class, not object
4. `final`: Cannot be changed
5. Use `this` to distinguish variables and chain constructors
6. Use `super` to call parent methods/constructors
7. Use `static` for shared data and utility methods
8. Use `final` for constants and immutability

## Practice

1. Create class with overloaded constructors using `this()`
2. Create parent-child classes and use `super`
3. Create utility class with `static` methods
4. Create class with `final` constants
5. Demonstrate `static` counter shared by all objects
