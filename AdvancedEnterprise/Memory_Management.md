# Memory Management

## Java Memory Architecture

Java manages memory automatically! You don't allocate/free memory manually like in C/C++.

```
JVM Memory
| Stack Memory
|   | Method calls, local variables
| Heap Memory
    | Young Generation (Eden, S0, S1)
    | Old Generation
    | Metaspace (class metadata)
```

## 1. Stack Memory

Stores **method calls** and **local variables**.

### Characteristics
- Fast access
- Fixed size per thread
- Automatic allocation/deallocation
- Thread-specific

### Example
```java
public class StackDemo {
    public static void main(String[] args) {
        int num = 10;          // Stored in stack
        String str = "Hello";  // Reference in stack, object in heap
        
        calculateSum(5, 3);
    }
    
    public static int calculateSum(int a, int b) {
        int sum = a + b;  // Stored in stack
        return sum;
    }
}
```

```
Stack (main thread):
||
| num = 10    |
| str → [heap]|
||

Stack (calculateSum):
||
| a = 5       |
| b = 3       |
| sum = 8     |
||
```

## 2. Heap Memory

Stores **objects** and **instance variables**.

### Characteristics
- Shared among threads
- Larger than stack
- Slower than stack
- Garbage collected

### Example
```java
public class HeapDemo {
    public static void main(String[] args) {
        // Object created in heap
        Student student = new Student("Alice", 20);
        
        // Reference in stack, object in heap
        student.display();
    }
}

class Student {
    String name;
    int age;
    
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

```
Heap:
||
| Student Object     |
| name: "Alice"      |
| age: 20            |
||
```

## 3. String Pool

Special area in heap for String literals.

```java
String s1 = "Hello";  // Created in String Pool
String s2 = "Hello";  // Reuses from pool (same object!)
String s3 = new String("Hello");  // New object in heap
```

## Garbage Collection (GC)

Automatically removes **unused objects** from heap.

### How GC Works

1. Object is created in heap
2. If no references point to it → eligible for GC
3. GC runs periodically and removes it
4. Memory freed for new objects

### Making Object Eligible for GC
```java
public class GCDemo {
    public static void main(String[] args) {
        Student student = new Student("Alice");
        student = null;  // Now eligible for GC
    }
}
```

### Requesting GC
```java
System.gc();  // Suggests GC, but not guaranteed
```

## Heap Structure (Generations)

### Young Generation
- New objects created here
- Divided into Eden, S0, S1 (Survivor spaces)
- Minor GC runs frequently

### Old Generation (Tenured)
- Long-lived objects promoted here
- Major GC runs less frequently (slower)

### Metaspace (Method Area)
- Class metadata, static variables
- Replaces PermGen (Java 8+)

## Object Lifecycle

```
Creation (new)
    v
Eden (Young Gen)
    v (survives Minor GC)
Survivor Space
    v (survives multiple GCs)
Old Generation
    v (eligible for GC when no references)
Garbage Collection
    v
Memory Freed
```

## Memory Leaks

When objects are **not garbage collected** even though unused.

### Common Causes
1. Static collections holding references
2. Unclosed resources (connections, streams)
3. Listeners not deregistered

```java
public class MemoryLeakDemo {
    static List<Object> cache = new ArrayList<>();  // Never cleared!
    
    public void process() {
        Object obj = new Object();
        cache.add(obj);  // Stays forever
    }
}
```

## Best Practices

1. **Close resources in finally or try-with-resources**
```java
try (Connection conn = getConnection()) {
    // use connection
}  // Auto-closed
```

2. **Avoid memory leaks**
```java
// Clean up static collections
cache.clear();
```

3. **Use weak references if needed**
```java
WeakReference<Object> ref = new WeakReference<>(new Object());
```

## Monitoring Memory

```java
// Get JVM memory info
Runtime runtime = Runtime.getMemory();
long maxMemory = runtime.maxMemory();
long totalMemory = runtime.totalMemory();
long freeMemory = runtime.freeMemory();

System.out.println("Max: " + maxMemory / 1024 / 1024 + " MB");
System.out.println("Used: " + (totalMemory - freeMemory) / 1024 / 1024 + " MB");
```

## Common Issues

### OutOfMemoryError
```java
// Create too many objects
List<byte[]> list = new ArrayList<>();
while (true) {
    list.add(new byte[1024 * 1024]);  // 1MB each
}
// Eventually: java.lang.OutOfMemoryError
```

### StackOverflowError
```java
public class StackOverflow {
    public static void recurse() {
        recurse();  // Infinite recursion
    }
}
```

## Key Takeaways

1. **Stack**: Fast, local variables, thread-specific
2. **Heap**: Objects, shared, GC managed
3. **String Pool**: Reuses string literals
4. **GC**: Automatically frees unused memory
5. **Generations**: Young, Old, Metaspace
6. Avoid memory leaks by cleaning references

## Practice

1. Understand stack vs heap with example
2. Observe garbage collection with System.gc()
3. Monitor memory usage of your program
4. Try creating OutOfMemoryError (carefully!)
5. Practice with try-with-resources
