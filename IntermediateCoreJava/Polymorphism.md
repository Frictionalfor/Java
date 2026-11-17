# Polymorphism

## What is Polymorphism?

**Polymorphism** = "Many forms"

The ability of an object to take **many forms**. Same method name, different behavior.

Real-world example:
- **Shape** has method `draw()`
- **Circle** draws a circle
- **Rectangle** draws a rectangle
- **Triangle** draws a triangle

Same method name `draw()`, different behavior for each shape!

## Why Polymorphism?

1. **Flexibility**: Write code that works with parent type, works with all children
2. **Extensibility**: Add new child classes without changing existing code
3. **Maintainability**: Less code duplication

## Two Types of Polymorphism

### 1. Compile-Time Polymorphism (Method Overloading)
Same method name, different parameters. **Decided at compile time**.

```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }
    
    double add(double a, double b) {
        return a + b;
    }
    
    int add(int a, int b, int c) {
        return a + b + c;
    }
}

// Java decides which method to call based on arguments
Calculator calc = new Calculator();
calc.add(5, 3);          // Calls int version
calc.add(5.5, 3.5);      // Calls double version
calc.add(5, 3, 2);       // Calls 3-parameter version
```

### 2. Runtime Polymorphism (Method Overriding)
Same method signature, different implementation in parent-child. **Decided at runtime**.

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

// Runtime decides which method to call
Animal dog = new Dog();
Animal cat = new Cat();

dog.sound();  // Dog barks (decided at runtime)
cat.sound();  // Cat meows (decided at runtime)
```

## Runtime Polymorphism Deep Dive

### Upcasting
```java
Dog dog = new Dog();      // Dog reference, Dog object
Animal animal = new Dog(); // Animal reference, Dog object (UPCASTING)
```

**Upcasting:** Child object assigned to parent reference. Safe and implicit.

```java
Animal animal = new Dog();  // OK - Dog IS-A Animal
animal.sound();  // Dog barks (runtime decides)
```

### Downcasting
```java
Animal animal = new Dog();
Dog dog = (Dog) animal;  // DOWNCASTING - explicit cast needed
```

**Downcasting:** Parent reference to child reference. Risky, needs explicit cast.

```java
Animal animal = new Animal();
Dog dog = (Dog) animal;  // Runtime error!
```

**Always use `instanceof` before downcasting:**
```java
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;  // Safe
}
```

## Real-World Example: Payment System

```java
// Parent class
class Payment {
    void processPayment(double amount) {
        System.out.println("Processing payment: " + amount);
    }
}

// Child classes
class CreditCardPayment extends Payment {
    @Override
    void processPayment(double amount) {
        System.out.println("Processing credit card payment: " + amount);
        System.out.println("Adding credit card fees...");
    }
}

class PayPalPayment extends Payment {
    @Override
    void processPayment(double amount) {
        System.out.println("Processing PayPal payment: " + amount);
        System.out.println("Redirecting to PayPal...");
    }
}

class UpiPayment extends Payment {
    @Override
    void processPayment(double amount) {
        System.out.println("Processing UPI payment: " + amount);
        System.out.println("Scanning QR code...");
    }
}

// Usage
public class PaymentDemo {
    public static void process(Payment payment, double amount) {
        payment.processPayment(amount);  // Polymorphic call
    }
    
    public static void main(String[] args) {
        Payment creditCard = new CreditCardPayment();
        Payment paypal = new PayPalPayment();
        Payment upi = new UpiPayment();
        
        process(creditCard, 1000);  // Credit card processing
        process(paypal, 500);       // PayPal processing
        process(upi, 200);          // UPI processing
    }
}
```

**Benefit:** `process()` method works with ANY payment type!

## Dynamic Method Dispatch

The mechanism by which a call to an overridden method is resolved at runtime.

```java
class Animal {
    void sound() {
        System.out.println("Animal");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();  // Upcast
        a.sound();  // Dog (resolved at runtime)
    }
}
```

## Polymorphism with Interfaces

```java
interface Drawable {
    void draw();
}

class Circle implements Drawable {
    public void draw() {
        System.out.println("Drawing circle");
    }
}

class Rectangle implements Drawable {
    public void draw() {
        System.out.println("Drawing rectangle");
    }
}

public class Main {
    public static void main(String[] args) {
        Drawable d1 = new Circle();
        Drawable d2 = new Rectangle();
        
        d1.draw();  // Drawing circle
        d2.draw();  // Drawing rectangle
    }
}
```

## Polymorphism with Arrays

```java
Animal[] animals = new Animal[3];
animals[0] = new Dog();
animals[1] = new Cat();
animals[2] = new Dog();

for (Animal animal : animals) {
    animal.sound();  // Each animal makes its own sound
}
```

## Advantages of Polymorphism

1. **Code Reusability**: Write once, use many times
2. **Single Interface**: Same interface for different implementations
3. **Extensibility**: Add new classes without changing existing code
4. **Flexibility**: Easy to maintain and modify

## Example: Media Player

```java
class MediaPlayer {
    void play(String audioType, String fileName) {
        System.out.println("Playing media");
    }
}

class AudioPlayer extends MediaPlayer {
    @Override
    void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3: " + fileName);
        } else {
            System.out.println("Unsupported format");
        }
    }
}

class VideoPlayer extends MediaPlayer {
    @Override
    void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp4")) {
            System.out.println("Playing MP4: " + fileName);
        } else {
            System.out.println("Unsupported format");
        }
    }
}

// Usage
MediaPlayer player = new AudioPlayer();
player.play("mp3", "song.mp3");  // Playing MP3

player = new VideoPlayer();
player.play("mp4", "movie.mp4");  // Playing MP4
```

## Key Takeaways

1. **Compile-time**: Method overloading (same name, different params)
2. **Runtime**: Method overriding (same signature, different behavior)
3. **Upcasting**: Child → Parent (safe, implicit)
4. **Downcasting**: Parent → Child (risky, needs `instanceof`)
5. **Benefit**: Write flexible, extensible code
6. **Dynamic dispatch**: Method call resolved at runtime

## Practice

1. Create `Shape` class with `area()` method
2. Create `Circle`, `Rectangle`, `Triangle` subclasses
3. Override `area()` in each
4. Create array of shapes, calculate areas polymorphically
5. Demonstrate upcasting and downcasting
