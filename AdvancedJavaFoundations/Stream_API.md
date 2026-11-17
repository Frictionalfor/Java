# Stream API

## What is a Stream?

A Stream is a **sequence of elements** that supports **functional-style operations** to process collections.

Think of it as a **pipeline** where data flows through stages (filter, map, collect).

```
Collection → Stream → Filter → Map → Collect → Result
```

## Why Streams?

### Before Streams (Imperative)
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenNumbers = new ArrayList<>();

for (int num : numbers) {
    if (num % 2 == 0) {
        evenNumbers.add(num);
    }
}
```

### With Streams (Declarative)
```java
List<Integer> evenNumbers = numbers.stream()
    .filter(num → num % 2 == 0)
    .collect(Collectors.toList());
```

Much cleaner and readable!

## Stream Operations

### Creating Streams

```java
// From Collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();

// From Array
String[] arr = {"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);

// Using Stream.of()
Stream<String> stream = Stream.of("a", "b", "c");
```

## Common Intermediate Operations

### `filter()` - Keep elements matching condition
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

List<Integer> even = numbers.stream()
    .filter(n → n % 2 == 0)
    .collect(Collectors.toList());
// Result: [2, 4, 6]
```

### `map()` - Transform each element
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

List<Integer> squares = numbers.stream()
    .map(n → n * n)
    .collect(Collectors.toList());
// Result: [1, 4, 9, 16, 25]
```

### `sorted()` - Sort elements
```java
List<String> names = Arrays.asList("Charlie", "Alice", "Bob");

List<String> sorted = names.stream()
    .sorted()
    .collect(Collectors.toList());
// Result: [Alice, Bob, Charlie]
```

### `distinct()` - Remove duplicates
```java
List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4);

List<Integer> unique = numbers.stream()
    .distinct()
    .collect(Collectors.toList());
// Result: [1, 2, 3, 4]
```

### `limit()` - Limit number of elements
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

List<Integer> firstThree = numbers.stream()
    .limit(3)
    .collect(Collectors.toList());
// Result: [1, 2, 3]
```

### `skip()` - Skip elements
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

List<Integer> skipTwo = numbers.stream()
    .skip(2)
    .collect(Collectors.toList());
// Result: [3, 4, 5]
```

## Common Terminal Operations

### `forEach()` - Process each element
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

names.stream()
    .forEach(name → System.out.println(name));
```

### `collect()` - Convert to collection
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

Set<Integer> set = numbers.stream()
    .collect(Collectors.toSet());
```

### `count()` - Count elements
```java
long count = numbers.stream()
    .filter(n → n > 3)
    .count();
// Result: 2
```

### `findFirst()` - Find first element
```java
Optional<Integer> first = numbers.stream()
    .filter(n → n > 3)
    .findFirst();
// Result: Optional[4]
```

### `anyMatch()`, `allMatch()`, `noneMatch()`
```java
boolean anyEven = numbers.stream()
    .anyMatch(n → n % 2 == 0);  // true if any even

boolean allPositive = numbers.stream()
    .allMatch(n → n > 0);  // true if all positive

boolean noneNegative = numbers.stream()
    .noneMatch(n → n < 0);  // true if none negative
```

## Reduction Operations

### `reduce()` - Combine elements
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Sum all
int sum = numbers.stream()
    .reduce(0, Integer::sum);
// Result: 15

// Find max
Optional<Integer> max = numbers.stream()
    .reduce(Integer::max);
// Result: Optional[5]
```

## Complete Examples

### Example 1: Process Students
```java
import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    int marks;
    
    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StreamDemo {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 95),
            new Student("Bob", 82),
            new Student("Charlie", 78),
            new Student("David", 90)
        );
        
        // Get names of students with marks > 85
        List<String> topStudents = students.stream()
            .filter(s → s.marks > 85)
            .map(s → s.name)
            .sorted()
            .collect(Collectors.toList());
        
        System.out.println(topStudents);  // [Alice, David]
        
        // Calculate average marks
        double average = students.stream()
            .mapToInt(s → s.marks)
            .average()
            .orElse(0);
        
        System.out.println("Average: " + average);
    }
}
```

### Example 2: Multiple Operations
```java
public class StreamDemo2 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        long count = numbers.stream()
            .filter(n → n % 2 == 0)      // Keep even
            .map(n → n * n)              // Square them
            .filter(n → n > 20)          // Keep > 20
            .count();
        
        System.out.println("Count: " + count);  // 3 (36, 64, 100)
    }
}
```

## Stream Types

### Sequential Stream
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()  // Sequential
    .forEach(System.out::println);
```

### Parallel Stream
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.parallelStream()  // Parallel processing
    .forEach(System.out::println);
```

**Use parallel streams for large datasets!**

## Lazy Evaluation

Intermediate operations are **lazy** - they execute only when terminal operation is called.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

Stream<Integer> stream = numbers.stream()
    .filter(n → {
        System.out.println("Filter: " + n);
        return n % 2 == 0;
    })
    .map(n → {
        System.out.println("Map: " + n);
        return n * n;
    });
// Nothing printed yet!

// Terminal operation triggers execution
stream.forEach(System.out::println);
// Now operations execute
```

## Key Takeaways

1. Streams process data in **pipeline** fashion
2. **Intermediate operations** return new stream (lazy)
3. **Terminal operations** produce result (trigger execution)
4. `filter()` - select elements
5. `map()` - transform elements
6. `collect()` - gather results
7. Streams can't be reused after terminal operation

## Practice

1. Filter list to get only even numbers
2. Transform list of strings to uppercase
3. Calculate sum of squares of numbers
4. Count words starting with specific letter
5. Find average of numbers in list
