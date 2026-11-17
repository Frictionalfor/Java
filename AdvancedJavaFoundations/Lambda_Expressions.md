# Lambda Expressions

## What is a Lambda Expression?

Lambda expressions provide a **clear and concise way** to represent a function/method. They enable **functional programming** in Java.

Think of it as **anonymous functions** - functions without a name.

## Why Lambdas?

### Before Java 8 (Anonymous Classes - Verbose)
```java
// Anonymous class implementing Runnable
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running...");
    }
};
```

### With Lambda (Clean!)
```java
Runnable r = () → System.out.println("Running...");
```

Much shorter and cleaner!

## Lambda Syntax

```
(parameters) → expression
```

or

```
(parameters) → { statements; }
```

### Components
1. **Parameters**: `(int a, int b)` or `(a, b)`
2. **Arrow**: `→`
3. **Body**: Expression or block

## Lambda Examples

### No Parameters
```java
// Runnable interface has no parameters
Runnable r = () → System.out.println("Hello");
r.run();  // Hello
```

### One Parameter
```java
// Parameter type can be inferred
Consumer<String> printer = msg → System.out.println(msg);
printer.accept("Hello World");
```

### Multiple Parameters
```java
BinaryOperator<Integer> add = (a, b) → a + b;
System.out.println(add.apply(5, 3));  // 8
```

### With Body Block
```java
Comparator<String> comparator = (s1, s2) → {
    if (s1.length() > s2.length()) {
        return 1;
    } else if (s1.length() < s2.length()) {
        return -1;
    } else {
        return 0;
    }
};
```

## Functional Interfaces

Lambda can only be used with **functional interfaces** - interfaces with exactly ONE abstract method.

### Common Functional Interfaces

| Interface | Method | Purpose |
|-----------|--------|---------|
| `Predicate<T>` | `boolean test(T t)` | Test condition, return boolean |
| `Consumer<T>` | `void accept(T t)` | Accept value, no return |
| `Supplier<T>` | `T get()` | Supply/return value |
| `Function<T,R>` | `R apply(T t)` | Transform T to R |
| `BiFunction<T,U,R>` | `R apply(T t, U u)` | Transform two values |

### Creating Custom Functional Interface
```java
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

public class LambdaDemo {
    public static void main(String[] args) {
        // Lambda implementing MathOperation
        MathOperation addition = (a, b) → a + b;
        MathOperation subtraction = (a, b) → a - b;
        
        System.out.println(addition.operate(10, 5));   // 15
        System.out.println(subtraction.operate(10, 5)); // 5
    }
}
```

## Practical Examples

### Example 1: Sorting with Lambda
```java
import java.util.*;

public class SortDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
        
        // Sort by length
        names.sort((s1, s2) → s1.length() - s2.length());
        System.out.println(names);  // [Bob, Alice, Charlie]
        
        // Sort alphabetically
        names.sort((s1, s2) → s1.compareTo(s2));
        System.out.println(names);  // [Alice, Bob, Charlie]
    }
}
```

### Example 2: Event Handling (GUI)
```java
// Traditional way
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked!");
    }
});

// With lambda
button.addActionListener(e → System.out.println("Clicked!"));
```

### Example 3: Processing List
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Print all
numbers.forEach(n → System.out.println(n));

// Print even numbers
numbers.forEach(n → {
    if (n % 2 == 0) {
        System.out.println(n);
    }
});
```

## Method References

Shorthand for lambdas that call existing methods.

```java
// Lambda
list.forEach(item → System.out.println(item));

// Method reference (same thing!)
list.forEach(System.out::println);
```

### Types of Method References

```java
// Static method
Math::sqrt

// Instance method of particular object
System.out::println

// Instance method of arbitrary object
String::toUpperCase

// Constructor
ArrayList::new
```

## Lambda with Collections

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// forEach
names.forEach(name → System.out.println(name));

// Remove if
names.removeIf(name → name.startsWith("A"));

// Replace all
names.replaceAll(name → name.toUpperCase());
```

## Lambda Best Practices

1. **Keep it simple** - If lambda is long, use method reference
2. **Use meaningful parameter names**
   ```java
   // Good
   (firstName, lastName) → firstName + " " + lastName
   
   // Bad
   (a, b) → a + " " + b
   ```
3. **Avoid side effects** - Lambdas should be pure functions

## Lambda Rules

1. Parameter types can be inferred
   ```java
   // (Integer a, Integer b) is same as (a, b)
   (a, b) → a + b
   ```

2. Curly braces optional for single expression
   ```java
   // Both work
   n → System.out.println(n)
   n → { System.out.println(n); }
   ```

3. Return keyword optional for single expression
   ```java
   // Both work
   (a, b) → { return a + b; }
   (a, b) → a + b
   ```

4. One parameter doesn't need parentheses
   ```java
   // Both work
   (name) → System.out.println(name)
   name → System.out.println(name)
   ```

## Key Takeaways

1. Lambda = anonymous function
2. Works with functional interfaces (one abstract method)
3. Syntax: `(params) → body`
4. Makes code concise and readable
5. Use with Collections, Threads, event handlers
6. Method references: `System.out::println`

## Practice

1. Create functional interface for arithmetic operations
2. Implement with lambdas for add, subtract, multiply, divide
3. Sort list of strings using lambda comparator
4. Use forEach with lambda to print collection
5. Use removeIf with lambda to filter list
