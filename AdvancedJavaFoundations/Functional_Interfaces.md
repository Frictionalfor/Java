# Built-in Functional Interfaces

## What are Functional Interfaces?

Functional interfaces have **exactly ONE abstract method**. They're the foundation of lambda expressions.

```
@FunctionalInterface
interface MyInterface {
    void doSomething();  // Only ONE abstract method
}
```

Java provides many built-in functional interfaces in `java.util.function` package.

## Core Functional Interfaces

### 1. Predicate<T> - Test Condition

Takes one parameter, returns boolean.

```java
Predicate<Integer> isEven = num → num % 2 == 0;
System.out.println(isEven.test(10));  // true
System.out.println(isEven.test(7));   // false
```

**Common use:** Filtering collections

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> evens = numbers.stream()
    .filter(isEven)
    .collect(Collectors.toList());
```

### 2. Consumer<T> - Accept and Process

Takes one parameter, returns nothing (void).

```java
Consumer<String> printMessage = msg → System.out.println(msg);
printMessage.accept("Hello World");  // Hello World
```

**Common use:** Iterating and printing

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach(name → System.out.println(name));
```

### 3. Supplier<T> - Supply Value

Takes no parameters, returns a value.

```java
Supplier<Double> randomValue = () → Math.random();
System.out.println(randomValue.get());  // 0.8234...
```

**Common use:** Lazy evaluation, generating values

```java
Supplier<String> greeting = () → "Hello!";
System.out.println(greeting.get());  // Hello!
```

### 4. Function<T, R> - Transform

Takes one parameter of type T, returns value of type R.

```java
Function<Integer, String> intToString = num → "Number: " + num;
System.out.println(intToString.apply(42));  // Number: 42
```

**Common use:** Mapping/transforming data

```java
List<Integer> numbers = Arrays.asList(1, 2, 3);
List<String> strings = numbers.stream()
    .map(num → "Value: " + num)
    .collect(Collectors.toList());
```

## More Functional Interfaces

### BiPredicate<T, U>
Two parameters, returns boolean.

```java
BiPredicate<Integer, Integer> isGreater = (a, b) → a > b;
System.out.println(isGreater.test(10, 5));  // true
```

### BiConsumer<T, U>
Two parameters, returns nothing.

```java
BiConsumer<String, Integer> printNameAge = (name, age) → 
    System.out.println(name + " is " + age + " years old");
printNameAge.accept("Alice", 25);  // Alice is 25 years old
```

### BiFunction<T, U, R>
Two parameters, returns value.

```java
BiFunction<Integer, Integer, Integer> add = (a, b) → a + b;
System.out.println(add.apply(5, 3));  // 8
```

### UnaryOperator<T>
One parameter, returns same type.

```java
UnaryOperator<Integer> square = n → n * n;
System.out.println(square.apply(5));  // 25
```

### BinaryOperator<T>
Two parameters of same type, returns same type.

```java
BinaryOperator<Integer> multiply = (a, b) → a * b;
System.out.println(multiply.apply(4, 5));  // 20
```

## Practical Examples

### Example 1: Filtering with Predicate
```java
import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        // Check if number is positive
        Predicate<Integer> isPositive = n → n > 0;
        
        // Check if string is empty
        Predicate<String> isEmpty = str → str.isEmpty();
        
        // Combine predicates
        Predicate<Integer> isEven = n → n % 2 == 0;
        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
        
        System.out.println(isPositiveAndEven.test(4));   // true
        System.out.println(isPositiveAndEven.test(3));   // false
        System.out.println(isPositiveAndEven.test(-2));  // false
    }
}
```

### Example 2: Chaining Functions
```java
import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        // Function 1: Convert to uppercase
        Function<String, String> toUpperCase = str → str.toUpperCase();
        
        // Function 2: Add exclamation
        Function<String, String> addExclamation = str → str + "!";
        
        // Chain functions
        Function<String, String> combined = toUpperCase.andThen(addExclamation);
        
        System.out.println(combined.apply("hello"));  // HELLO!
    }
}
```

### Example 3: Using Multiple Interfaces Together
```java
import java.util.function.*;

public class CombinedDemo {
    public static void main(String[] args) {
        // Predicate
        Predicate<Integer> isEven = n → n % 2 == 0;
        
        // Function
        Function<Integer, Integer> square = n → n * n;
        
        // Consumer
        Consumer<Integer> print = n → System.out.println(n);
        
        // Supplier
        Supplier<Double> random = () → Math.random();
        
        // Use them together
        int num = 5;
        if (isEven.test(num)) {
            print.accept(square.apply(num));
        } else {
            System.out.println("Random: " + random.get());
        }
    }
}
```

## Key Takeaways

1. **Predicate<T>**: Test condition, return boolean
2. **Consumer<T>**: Accept value, return nothing
3. **Supplier<T>**: No input, return value
4. **Function<T, R>**: Transform T to R
5. All are in `java.util.function` package
6. Enable lambda expressions and functional programming
7. Can be combined with `and()`, `or()`, `negate()`, `andThen()`

## Practice

1. Create Predicate to check if number is prime
2. Create Consumer to print formatted student info
3. Create Supplier to generate random password
4. Create Function to convert temperature C to F
5. Chain multiple functions together
