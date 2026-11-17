# Abstraction

## What is Abstraction?

Abstraction means **hiding implementation details** and showing only **essential features**.

Real-world example:
- **Car**: You know how to drive (gas, brake, steering)
- **You don't need to know**: How engine works internally, how fuel injection works
- **Abstraction**: Shows what to do, hides how it's done

## Why Abstraction?

1. **Simplifies complexity**: Hide messy details
2. **Security**: Prevent misuse of internal details
3. **Flexibility**: Change implementation without affecting users
4. **Focus**: Users focus on "what", not "how"

## Ways to Achieve Abstraction in Java

1. **Abstract Classes** (0-100% abstraction)
2. **Interfaces** (100% abstraction, before Java 8)

## 1. Abstract Classes

An abstract class cannot be instantiated. It's meant to be **extended**.

### Declaring Abstract Class
```java
abstract class Animal {
    // Abstract method (no implementation)
    abstract void sound();
    
    // Concrete method (has implementation)
    void sleep() {
        System.out.println("Animal is sleeping");
    }
}
```

### Implementing Abstract Class
```java
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
```

### Using Abstract Class
```java
public class Main {
    public static void main(String[] args) {
        // Animal animal = new Animal();  // Error! Cannot instantiate
        
        Animal dog = new Dog();  // OK
        Animal cat = new Cat();  // OK
        
        dog.sound();  // Dog barks
        cat.sound();  // Cat meows
    }
}
```

## Abstract Methods vs Concrete Methods

```java
abstract class Shape {
    // Abstract method - MUST be overridden
    abstract double area();
    
    // Concrete method - can be used directly
    void display() {
        System.out.println("This is a shape");
    }
}

class Circle extends Shape {
    double radius;
    
    Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}
```

## Rules for Abstract Classes

1. **Cannot create object** of abstract class
2. **Can have abstract and concrete methods**
3. **If class has abstract method, class must be abstract**
4. **Child must implement all abstract methods** OR be abstract itself

```java
abstract class Vehicle {
    abstract void start();  // Abstract
    
    void stop() {           // Concrete
        System.out.println("Stopping");
    }
}

// Must implement start()
class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car started");
    }
}
```

## 2. Interfaces

An interface is a **contract** that defines what a class must do, not how.

### Declaring Interface
```java
interface Vehicle {
    void start();      // Implicitly public abstract
    void stop();       // Implicitly public abstract
}
```

### Implementing Interface
```java
class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }
    
    @Override
    public void stop() {
        System.out.println("Car stopped");
    }
}
```

### Multiple Interfaces
```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Duck is flying");
    }
    
    @Override
    public void swim() {
        System.out.println("Duck is swimming");
    }
}
```

## Interface Evolution (Java 8+)

### Default Methods
Add methods with implementation to interfaces.

```java
interface Vehicle {
    void start();  // Abstract
    
    default void honk() {  // Default method with implementation
        System.out.println("Beep beep!");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car started");
    }
    
    // Can use default honk() or override it
}

Car car = new Car();
car.start();  // Car started
car.honk();   // Beep beep!
```

### Static Methods
```java
interface MathUtils {
    static int add(int a, int b) {
        return a + b;
    }
}

// Call directly on interface
int sum = MathUtils.add(5, 3);
```

## Abstract Class vs Interface

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Multiple inheritance | No | Yes |
| Constructor | Yes | No |
| Abstract methods | Can mix concrete | All abstract (before Java 8) |
| Variables | Any type | Only `public static final` |
| Use when | Shared code among related classes | Define contract for unrelated classes |

## When to Use What?

### Use Abstract Class When:
- Classes share common code
- You want to share state (variables)
- Related classes (IS-A relationship)
- Need constructor

### Use Interface When:
- Unrelated classes need same behavior
- Need multiple inheritance
- Define contract only
- Future extensibility important

## Complete Example: Banking System

```java
// Abstract class
abstract class Account {
    protected String accountNumber;
    protected double balance;
    
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    abstract void calculateInterest();  // Each account type calculates differently
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
    }
    
    public void displayBalance() {
        System.out.println("Balance: " + balance);
    }
}

// Concrete class 1
class SavingsAccount extends Account {
    private double interestRate;
    
    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }
    
    @Override
    void calculateInterest() {
        double interest = balance * interestRate / 100;
        System.out.println("Savings interest: " + interest);
    }
}

// Concrete class 2
class CurrentAccount extends Account {
    public CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }
    
    @Override
    void calculateInterest() {
        System.out.println("No interest for current account");
    }
}

// Usage
public class BankDemo {
    public static void main(String[] args) {
        Account savings = new SavingsAccount("S123", 10000, 5);
        Account current = new CurrentAccount("C456", 5000);
        
        savings.deposit(2000);
        savings.calculateInterest();  // Savings interest: 600.0
        
        current.deposit(1000);
        current.calculateInterest();  // No interest for current account
    }
}
```

## Interface Example: Payment Gateway

```java
interface PaymentGateway {
    void processPayment(double amount);
    void refund(double amount);
    double checkBalance();
}

class StripeGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("Stripe processing: " + amount);
    }
    
    @Override
    public void refund(double amount) {
        System.out.println("Stripe refunding: " + amount);
    }
    
    @Override
    public double checkBalance() {
        return 10000;
    }
}

class PayPalGateway implements PaymentGateway {
    @Override
    public void processPayment(double amount) {
        System.out.println("PayPal processing: " + amount);
    }
    
    @Override
    public void refund(double amount) {
        System.out.println("PayPal refunding: " + amount);
    }
    
    @Override
    public double checkBalance() {
        return 5000;
    }
}

// Usage
public class PaymentDemo {
    public static void process(PaymentGateway gateway, double amount) {
        gateway.processPayment(amount);
    }
    
    public static void main(String[] args) {
        PaymentGateway stripe = new StripeGateway();
        PaymentGateway paypal = new PayPalGateway();
        
        process(stripe, 1000);   // Stripe processing: 1000
        process(paypal, 500);    // PayPal processing: 500
    }
}
```

## Key Takeaways

1. **Abstraction** = Hide details, show essentials
2. **Abstract class**: 0-100% abstraction, use `extends`
3. **Interface**: 100% abstraction (mostly), use `implements`
4. **Abstract methods**: No body, must be overridden
5. **Concrete methods**: Have body, can be inherited
6. Cannot create object of abstract class
7. Child must implement all abstract methods

## Practice

1. Create `Employee` abstract class with abstract `calculateSalary()`
2. Create `FullTimeEmployee` and `PartTimeEmployee` subclasses
3. Implement `calculateSalary()` differently for each
4. Create interface `Printable` with `print()` method
5. Implement in multiple classes
