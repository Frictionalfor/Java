# Generics

## What are Generics?

Generics enable **type-safe code** by allowing you to write classes, interfaces, and methods that work with **any data type**.

Think of it like a **container that can hold any type**, but you specify what type when you use it.

## Why Use Generics?

### Without Generics (Problems)
```java
List names = new ArrayList();
names.add("Alice");
names.add("Bob");
names.add(100);  // Oops! Added integer to string list

String name = (String) names.get(2);  // Runtime error!
```

### With Generics (Solution)
```java
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");
// names.add(100);  // Compile-time error! Type safe

String name = names.get(0);  // No casting needed
```

## Benefits of Generics

1. **Type Safety**: Errors caught at compile time
2. **No Casting**: Automatic type conversion
3. **Reusability**: Write once, use with any type
4. **Better Readability**: Clear what type is being used

## Generic Class

### Syntax
```java
class ClassName<T> {
    private T value;
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
}
```

`<T>` is called a **type parameter** (T stands for Type)

### Example
```java
// Generic Box class
class Box<T> {
    private T item;
    
    public Box(T item) {
        this.item = item;
    }
    
    public T getItem() {
        return item;
    }
    
    public void setItem(T item) {
        this.item = item;
    }
}

// Usage
Box<String> stringBox = new Box<>("Hello");
Box<Integer> intBox = new Box<>(100);
Box<Double> doubleBox = new Box<>(99.99);

System.out.println(stringBox.getItem());  // Hello
System.out.println(intBox.getItem());      // 100
```

## Generic Method

Method with its own type parameter.

```java
public class GenericMethod {
    // Generic method
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
    
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3};
        String[] names = {"Alice", "Bob", "Charlie"};
        
        printArray(numbers);  // Works with Integer[]
        printArray(names);    // Works with String[]
    }
}
```

## Multiple Type Parameters

```java
class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
}

// Usage
Pair<String, Integer> student = new Pair<>("Alice", 101);
System.out.println(student.getKey());    // Alice
System.out.println(student.getValue());  // 101
```

## Bounded Type Parameters

Restrict types that can be used.

### Upper Bound (extends)
```java
// Only accepts Number and its subclasses
class NumberBox<T extends Number> {
    private T number;
    
    public NumberBox(T number) {
        this.number = number;
    }
    
    public double square() {
        return number.doubleValue() * number.doubleValue();
    }
}

// Usage
NumberBox<Integer> intBox = new NumberBox<>(5);
NumberBox<Double> doubleBox = new NumberBox<>(3.14);

System.out.println(intBox.square());    // 25.0
System.out.println(doubleBox.square()); // 9.8596

// NumberBox<String> stringBox = new NumberBox<>("Hello");  // Error!
```

### Multiple Bounds
```java
// Must extend Number AND implement Comparable
class BoundedBox<T extends Number & Comparable<T>> {
    // ...
}
```

## Wildcards

`?` represents unknown type.

### Unbounded Wildcard
```java
// Accepts any type
public static void printList(List<?> list) {
    for (Object element : list) {
        System.out.println(element);
    }
}
```

### Upper Bounded Wildcard (`? extends`)
```java
// Accepts Number and its subclasses
public static double sum(List<? extends Number> list) {
    double sum = 0;
    for (Number num : list) {
        sum += num.doubleValue();
    }
    return sum;
}
```

### Lower Bounded Wildcard (`? super`)
```java
// Accepts Integer and its superclasses
public static void addNumbers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
    list.add(3);
}

List<Number> numbers = new ArrayList<>();
addNumbers(numbers);  // OK

List<Object> objects = new ArrayList<>();
addNumbers(objects);  // OK
```

## Generic Interface

```java
interface Container<T> {
    void add(T item);
    T get(int index);
}

class MyContainer<T> implements Container<T> {
    private List<T> items = new ArrayList<>();
    
    @Override
    public void add(T item) {
        items.add(item);
    }
    
    @Override
    public T get(int index) {
        return items.get(index);
    }
}

// Usage
Container<String> container = new MyContainer<>();
container.add("Hello");
System.out.println(container.get(0));  // Hello
```

## Type Erasure

Java implements generics using **type erasure**:
- Type parameters removed at runtime
- Compiler inserts casts where needed
- Ensures backward compatibility with old Java

```java
// Your code
List<String> names = new ArrayList<>();
names.add("Alice");
String name = names.get(0);

// After compilation (conceptually)
List names = new ArrayList();
names.add("Alice");
String name = (String) names.get(0);
```

## Raw Types (Avoid!)

Using generic class without type parameter.

```java
List names = new ArrayList();  // Raw type - avoid!
names.add("Alice");
names.add(100);  // No compile error - unsafe!
```

## Common Generic Classes in Java

```java
List<String>        // Ordered, duplicates allowed
Set<Integer>        // Unordered, unique
Map<String, Integer> // Key-value pairs
Queue<String>       // FIFO
Pair<String, Integer> // Two values together
Optional<String>    // May or may not have value
```

## Practical Example: Generic Repository

```java
import java.util.*;

// Generic repository
class Repository<T, ID> {
    private Map<ID, T> items = new HashMap<>();
    
    public void add(ID id, T item) {
        items.put(id, item);
    }
    
    public T getById(ID id) {
        return items.get(id);
    }
    
    public void remove(ID id) {
        items.remove(id);
    }
    
    public List<T> getAll() {
        return new ArrayList<>(items.values());
    }
}

// Usage
class Product {
    private String name;
    private double price;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

public class GenericDemo {
    public static void main(String[] args) {
        Repository<Integer, Product> productRepo = new Repository<>();
        
        productRepo.add(1, new Product("Laptop", 999.99));
        productRepo.add(2, new Product("Mouse", 25.50));
        
        System.out.println(productRepo.getById(1));  // Laptop - $999.99
        System.out.println(productRepo.getAll());    // [Laptop - $999.99, Mouse - $25.50]
    }
}
```

## Key Takeaways

1. **Generics** provide type safety at compile time
2. `<T>` is type parameter, use any letter (T, E, K, V common)
3. **Bounded types**: `<T extends Number>` restricts to Number and subclasses
4. **Wildcards**: `?` for unknown types
5. **Type erasure**: Generics implemented at compile time only
6. **Avoid raw types** - always specify type parameter
7. Use generics with Collections for type safety

## Practice

1. Create generic Box class
2. Create generic Pair class with two types
3. Create bounded generic for numbers only
4. Create generic method to find maximum in array
5. Use wildcards with collections
