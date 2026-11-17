# Methods

## What is a Method?

A method is a reusable block of code that performs a specific task. Like a recipe - define it once, use it many times.

```java
public static int add(int a, int b) {
    return a + b;
}
```

## Why Use Methods?

- **Reusability**: Write once, use many times
- **Organization**: Break big problem into small pieces
- **Readability**: Code is easier to understand
- **Maintenance**: Fix in one place, works everywhere

## Method Structure

```
accessModifier static returnType methodName(parameters) {
    // method body
    return value;  // if returnType is not void
}
```

### Parts Explained
- **accessModifier**: `public`, `private`, etc.
- **static**: Belongs to class (use for now)
- **returnType**: What the method gives back (`int`, `String`, `void`)
- **methodName**: What you call it (camelCase)
- **parameters**: Input values

## Creating Methods

### Method with No Parameters, No Return
```java
public static void greet() {
    System.out.println("Hello, World!");
}
```

### Method with Parameters, No Return
```java
public static void greetPerson(String name) {
    System.out.println("Hello, " + name + "!");
}

// Call it
greetPerson("Alice");  // Hello, Alice!
```

### Method with Parameters and Return
```java
public static int add(int a, int b) {
    return a + b;
}

// Call it
int sum = add(5, 3);  // 8
```

## Calling Methods

```java
public class MethodDemo {
    public static void main(String[] args) {
        // Calling a method
        greet();
        
        // Calling with parameter
        greetPerson("Alice");
        
        // Calling with parameters and getting return
        int result = add(10, 20);
        System.out.println("Sum: " + result);
    }
    
    public static void greet() {
        System.out.println("Hello!");
    }
    
    public static void greetPerson(String name) {
        System.out.println("Hello, " + name + "!");
    }
    
    public static int add(int a, int b) {
        return a + b;
    }
}
```

## Method Overloading

Same method name, different parameters. Like multiple versions of same recipe.

```java
public class Calculator {
    // Version 1: Two integers
    public static int add(int a, int b) {
        return a + b;
    }
    
    // Version 2: Three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // Version 3: Two doubles
    public static double add(double a, double b) {
        return a + b;
    }
}
```

### How Java Chooses
```java
add(5, 3);        // Calls version 1 (two ints)
add(5, 3, 2);     // Calls version 2 (three ints)
add(5.5, 3.5);    // Calls version 3 (two doubles)
```

## Parameters vs Arguments

- **Parameters**: Variables in method definition
- **Arguments**: Actual values passed when calling

```java
public static void greet(String name) {  // 'name' is parameter
    System.out.println("Hello " + name);
}

greet("Alice");  // "Alice" is argument
```

## Return Statement

Method returns value to caller.

```java
public static int square(int num) {
    return num * num;  // Returns value
}

int result = square(5);  // result = 25
```

### Void Methods
```java
public static void printMessage(String msg) {
    System.out.println(msg);
    // No return statement needed
}
```

### Multiple Returns
```java
public static String getGrade(int marks) {
    if (marks >= 90) return "A";
    if (marks >= 80) return "B";
    if (marks >= 70) return "C";
    return "F";
}
```

## Pass by Value

Java always passes arguments **by value** (a copy).

```java
public static void main(String[] args) {
    int x = 10;
    modify(x);
    System.out.println(x);  // Still 10!
}

public static void modify(int num) {
    num = 100;  // Changes local copy only
}
```

## Variable Scope

Variables declared inside method are **local** - only accessible there.

```java
public static void main(String[] args) {
    int x = 10;  // Local to main()
    
    printNumber();
    // System.out.println(num);  // Error! num not accessible here
}

public static void printNumber() {
    int num = 20;  // Local to printNumber()
    System.out.println(num);
}
```

## Method Examples

### Example 1: Area of Rectangle
```java
public class Rectangle {
    public static double area(double length, double width) {
        return length * width;
    }
    
    public static void main(String[] args) {
        double area = area(5.0, 3.0);
        System.out.println("Area: " + area);  // 15.0
    }
}
```

### Example 2: Check Even/Odd
```java
public class EvenOdd {
    public static String checkEvenOdd(int num) {
        if (num % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    }
    
    public static void main(String[] args) {
        System.out.println(checkEvenOdd(10));  // Even
        System.out.println(checkEvenOdd(7));   // Odd
    }
}
```

### Example 3: Factorial
```java
public class Factorial {
    public static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("5! = " + factorial(5));   // 120
        System.out.println("10! = " + factorial(10)); // 3628800
    }
}
```

### Example 4: Prime Number Check
```java
public class PrimeCheck {
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("7 is prime: " + isPrime(7));    // true
        System.out.println("10 is prime: " + isPrime(10));  // false
    }
}
```

## Method Overloading vs Overriding

| Overloading | Overriding |
|-------------|------------|
| Same class | Parent-child classes |
| Same name, different params | Same name, same params |
| Compile-time | Runtime |
| Also called: Compile-time polymorphism | Also called: Runtime polymorphism |

## Common Mistakes

 **Forgetting return type**
```java
public static int add(int a, int b) {
    int sum = a + b;
    // Missing return statement!
}
```

 **Wrong parameter count**
```java
add(5, 3);      // OK
add(5);         // Error! Needs 2 parameters
add(5, 3, 2);   // Error! No 3-parameter version
```

## Key Takeaways

1. Methods make code reusable and organized
2. Method signature: name + parameter types
3. Overloading: Same name, different parameters
4. Parameters are passed by value
5. Variables inside method are local
6. Use return to send value back
7. void means no return value

## Practice

1. Create method to calculate square of a number
2. Create method to check if number is positive/negative/zero
3. Create method to find greatest of three numbers
4. Overload a method that adds numbers (int, double, three ints)
5. Create method to convert Celsius to Fahrenheit
