# Classes and Objects

## The Foundation of OOP

Java is an **Object-Oriented Programming** language. Everything revolves around objects!

```
Object-Oriented Programming = Objects + Classes
```

## What is a Class?

A class is a **blueprint** or **template** for creating objects.

Think of it like a house blueprint:
- Blueprint = Class
- Actual houses built from it = Objects

```java
public class Car {
    // Attributes (state)
    String color;
    String brand;
    int year;
    
    // Behavior (methods)
    void drive() {
        System.out.println("Car is driving");
    }
    
    void honk() {
        System.out.println("Beep beep!");
    }
}
```

## What is an Object?

An object is an **instance** of a class. It's the actual thing created from the blueprint.

```java
Car myCar = new Car();  // Create object
myCar.color = "Red";
myCar.brand = "Toyota";
myCar.year = 2022;

myCar.drive();  // Car is driving
myCar.honk();   // Beep beep!
```

## Anatomy of a Class

### 1. Attributes (State)
Variables that describe the object.

```java
public class Student {
    String name;      // What the student IS
    int age;          // What the student IS
    String grade;     // What the student IS
}
```

### 2. Methods (Behavior)
Actions the object can perform.

```java
public class Student {
    void study() {
        System.out.println("Studying...");
    }
    
    void attendClass() {
        System.out.println("Attending class...");
    }
}
```

## Creating Objects

### Using `new` Keyword
```java
// Syntax: ClassName objectName = new ClassName();
Car car = new Car();
```

### What Happens When You Use `new`
1. Memory allocated for object
2. Constructor called
3. Object reference returned

```
||
|   car       | |
|  (ref)      |               |
||               |
                              v
                    ||
                    |   Car Object     |
                    | color: "Red"     |
                    | brand: "Toyota"  |
                    | year: 2022       |
                    ||
```

## Constructors

Special method that runs when creating an object. Used to initialize it.

### Default Constructor
```java
public class Student {
    String name;
    int age;
    
    // Constructor
    public Student() {
        name = "Unknown";
        age = 0;
    }
}
```

### Parameterized Constructor
```java
public class Student {
    String name;
    int age;
    
    // Constructor with parameters
    public Student(String name, int age) {
        this.name = name;  // 'this' refers to current object
        this.age = age;
    }
}
```

### Using Constructor
```java
// Using default constructor
Student s1 = new Student();

// Using parameterized constructor
Student s2 = new Student("Alice", 20);
```

**Note:** If you don't define any constructor, Java provides a default one automatically. If you define one, Java doesn't provide default!

## Complete Example: Student Class

```java
public class Student {
    // Attributes (private for encapsulation)
    private String name;
    private int rollNumber;
    private double marks;
    
    // Constructor
    public Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
    
    // Methods (behavior)
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll: " + rollNumber);
        System.out.println("Marks: " + marks);
    }
    
    public double calculateGrade() {
        if (marks >= 90) return 4.0;
        if (marks >= 80) return 3.0;
        if (marks >= 70) return 2.0;
        if (marks >= 60) return 1.0;
        return 0.0;
    }
    
    // Getters and Setters (access private fields)
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getRollNumber() {
        return rollNumber;
    }
    
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    public double getMarks() {
        return marks;
    }
    
    public void setMarks(double marks) {
        this.marks = marks;
    }
}
```

### Using the Student Class
```java
public class Main {
    public static void main(String[] args) {
        // Create student object
        Student s1 = new Student("Alice", 101, 95.5);
        
        // Display info
        s1.displayInfo();
        
        // Calculate grade
        double grade = s1.calculateGrade();
        System.out.println("Grade Point: " + grade);
        
        // Using getters
        System.out.println("Name: " + s1.getName());
        
        // Using setters
        s1.setMarks(98.0);
        System.out.println("Updated marks: " + s1.getMarks());
    }
}
```

## The `this` Keyword

`this` refers to the current object. Used to:
1. Distinguish between instance variables and parameters
2. Pass current object as parameter
3. Call another constructor

### Example
```java
public class Student {
    String name;
    int age;
    
    public Student(String name, int age) {
        this.name = name;    // this.name = instance variable
        this.age = age;      // this.age = instance variable
    }
}
```

## Multiple Objects from One Class

```java
public class Main {
    public static void main(String[] args) {
        // Create multiple student objects
        Student s1 = new Student("Alice", 101, 95.5);
        Student s2 = new Student("Bob", 102, 87.3);
        Student s3 = new Student("Charlie", 103, 92.1);
        
        // Each object has its own data
        s1.displayInfo();
        s2.displayInfo();
        s3.displayInfo();
    }
}
```

## Object References

Variables hold **references** to objects, not the objects themselves.

```java
Car car1 = new Car();     // New object
Car car2 = car1;          // Same object (reference copy)

car2.color = "Blue";      // Changes car1 too!
System.out.println(car1.color);  // Blue
```

## Object Lifecycle

1. **Creation**: `new Car()`
2. **Usage**: Accessing methods/fields
3. **Destruction**: Garbage collector removes when no references

## Memory Visualization

```
Stack Memory:
||
|   car1      | |
|  (ref)      |      |
||      |
                     v
Heap Memory:
||
|   Car Object       |
| color: "Red"       |
| brand: "Toyota"    |
| year: 2022         |
||
```

## Types of Methods in Class

### Instance Methods
Belong to object, need object to call.

```java
public void drive() {  // Instance method
    System.out.println("Driving...");
}

Car car = new Car();
car.drive();  // Call via object
```

### Static Methods
Belong to class, call without object.

```java
public static void honk() {  // Static method
    System.out.println("Beep!");
}

Car.honk();  // Call via class name
```

## Key Takeaways

1. Class = Blueprint, Object = Instance
2. Objects have state (attributes) and behavior (methods)
3. Constructors initialize objects
4. Use `new` to create objects
5. `this` refers to current object
6. Multiple objects can be created from one class
7. Variables hold references, not actual objects

## Practice

1. Create a `Book` class with title, author, price
2. Create `Library` class that manages multiple books
3. Add methods to display book info, calculate discount
4. Create 3 book objects and display their details
