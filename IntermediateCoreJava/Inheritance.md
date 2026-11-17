# Inheritance

## What is Inheritance?

Inheritance allows a **child class** to acquire **properties and behaviors** from a **parent class**.

Real-world example:
- **Parent**: Vehicle
- **Child**: Car, Bike, Truck
- Car inherits common features from Vehicle, but adds its own

## Why Inheritance?

1. **Reusability**: Reuse code from parent class
2. **Less Code**: No need to rewrite common functionality
3. **Organization**: Logical hierarchy of classes
4. **Extensibility**: Easy to add new features

## Terminology

- **Parent/Superclass**: Class being inherited from
- **Child/Subclass**: Class that inherits
- **extends**: Keyword used for inheritance

## Creating Inheritance

### Basic Syntax
```java
class Parent {
    // Parent class members
}

class Child extends Parent {
    // Child gets all Parent members + its own
}
```

### Example: Vehicle Hierarchy
```java
// Parent class
class Vehicle {
    String brand;
    int year;
    
    void start() {
        System.out.println("Vehicle started");
    }
    
    void stop() {
        System.out.println("Vehicle stopped");
    }
}

// Child class
class Car extends Vehicle {
    int doors;
    
    void honk() {
        System.out.println("Car honks: Beep beep!");
    }
}

// Usage
Car myCar = new Car();
myCar.brand = "Toyota";    // Inherited from Vehicle
myCar.year = 2022;         // Inherited from Vehicle
myCar.start();             // Inherited from Vehicle
myCar.honk();              // Car's own method
```

## What Child Class Inherits

- **Inherited:**
  - Public and protected methods
  - Public and protected variables
  - Default (package-private) members if in same package

- **NOT Inherited:**
  - Private members
  - Constructors (but can be called using `super`)
  - Static members (shared, not inherited)

## Constructor Chaining

When child object is created, parent constructor runs first!

```java
class Vehicle {
    String brand;
    
    Vehicle(String brand) {
        this.brand = brand;
        System.out.println("Vehicle constructor");
    }
}

class Car extends Vehicle {
    int doors;
    
    Car(String brand, int doors) {
        super(brand);  // Call parent constructor FIRST
        this.doors = doors;
        System.out.println("Car constructor");
    }
}

Car car = new Car("Toyota", 4);
// Output:
// Vehicle constructor
// Car constructor
```

**Important:** `super()` must be the FIRST statement!

If you don't call `super()`, Java automatically adds it.

## The `super` Keyword

Used to access parent class members.

### Call Parent Constructor
```java
Car(String brand, int doors) {
    super(brand);  // Call Vehicle constructor
    this.doors = doors;
}
```

### Call Parent Method
```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    void sound() {
        super.sound();  // Call parent's sound()
        System.out.println("Dog barks");
    }
}
```

### Access Parent Variables
```java
class Person {
    protected String name = "Unknown";
}

class Student extends Person {
    String name = "Alice";
    
    void display() {
        System.out.println(super.name);  // Unknown (parent)
        System.out.println(this.name);   // Alice (current)
    }
}
```

## Method Overriding

Child class **redefines** parent class method.

```java
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

// Usage
Animal dog = new Dog();
Animal cat = new Cat();

dog.sound();  // Dog barks (overridden)
cat.sound();  // Cat meows (overridden)
```

### `@Override` Annotation

Always use `@Override` when overriding. It:
- Makes code clearer
- Catches errors if method signature doesn't match

## Types of Inheritance

### 1. Single Inheritance
One child inherits from one parent.

```java
class Animal { }
class Dog extends Animal { }
```

### 2. Multilevel Inheritance
Child inherits from parent, which inherits from grandparent.

```java
class Animal { }
class Dog extends Animal { }
class Puppy extends Dog { }
```

### 3. Hierarchical Inheritance
Multiple children inherit from one parent.

```java
class Vehicle { }
class Car extends Vehicle { }
class Bike extends Vehicle { }
```

### 4. Multiple Inheritance (Not Supported with Classes)

Java doesn't support multiple inheritance with classes to avoid ambiguity.

```java
// Error! Cannot extend multiple classes
class Child extends Parent1, Parent2 { }
```

**Solution:** Use interfaces instead.

## Inheritance Example: Complete

```java
// Parent class
class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// Child class
class Student extends Person {
    private int rollNumber;
    private double marks;
    
    public Student(String name, int age, int rollNumber, double marks) {
        super(name, age);  // Call parent constructor
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
    
    public int getRollNumber() {
        return rollNumber;
    }
    
    public double getMarks() {
        return marks;
    }
    
    @Override
    public void display() {
        super.display();  // Call parent's display
        System.out.println("Roll: " + rollNumber);
        System.out.println("Marks: " + marks);
    }
    
    public String getGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        return "B";
    }
}

// Usage
Student s1 = new Student("Alice", 20, 101, 95.5);
s1.display();
// Output:
// Name: Alice, Age: 20
// Roll: 101
// Marks: 95.5

System.out.println("Grade: " + s1.getGrade());  // A+
```

## Important Rules

1. **Child inherits non-private members**
2. **Parent constructor runs first** (using `super()`)
3. **Can override methods** (same signature)
4. **Cannot reduce visibility** when overriding
5. **Static methods cannot be overridden** (they're hidden)

## Common Mistake: Calling Child Method from Parent Reference

```java
Person p = new Student();  // OK - Student IS-A Person
p.display();  // Calls Student's display() (runtime polymorphism)
```

## Key Takeaways

1. Inheritance = IS-A relationship
2. Use `extends` keyword
3. Child inherits public/protected members from parent
4. Parent constructor runs first (use `super()`)
5. Can override methods with `@Override`
6. Java doesn't support multiple inheritance with classes
7. Use inheritance for "is-a" relationships

## Practice

1. Create `Animal` parent class with `sound()` method
2. Create `Dog`, `Cat`, `Cow` child classes
3. Override `sound()` in each child
4. Create array of animals, call `sound()` polymorphically
