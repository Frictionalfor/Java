# Optional Class

## The NullPointerException Problem

```java
String name = null;
System.out.println(name.length());  // NullPointerException! 
```

**NullPointerException** is the most common error in Java.

## What is Optional?

`Optional<T>` is a container that **may or may not contain a value**. It's a safer way to handle null values.

Think of it like a **box** that might be empty.

```
||
| Optional    |
|   "Alice"   | <- Has value
||

||
| Optional    |
|   (empty)   | <- No value
||
```

## Why Use Optional?

1. **Prevent NullPointerException**
2. **Clear intent**: "This value might be absent"
3. **Functional style**: Chain operations safely
4. **Better API design**: Explicit null handling

## Creating Optional Objects

### `Optional.empty()`
```java
Optional<String> empty = Optional.empty();
```

### `Optional.of()`
```java
String name = "Alice";
Optional<String> opt = Optional.of(name);  // Must not be null
```

### `Optional.ofNullable()`
```java
String name = null;
Optional<String> opt = Optional.ofNullable(name);  // Can be null
```

## Checking for Value

### `isPresent()` - Check if value exists
```java
Optional<String> opt = Optional.of("Alice");

if (opt.isPresent()) {
    System.out.println(opt.get());  // Alice
}
```

### `isEmpty()` - Check if empty (Java 11+)
```java
Optional<String> opt = Optional.empty();

if (opt.isEmpty()) {
    System.out.println("No value");
}
```

## Retrieving Value

### `get()` - Get value (risky!)
```java
Optional<String> opt = Optional.of("Alice");
String name = opt.get();  // "Alice"

Optional<String> empty = Optional.empty();
String name = empty.get();  // NoSuchElementException! 
```

**Never use `get()` without checking!**

### `orElse()` - Return value or default
```java
Optional<String> opt = Optional.ofNullable(null);
String name = opt.orElse("Default Name");
System.out.println(name);  // Default Name
```

### `orElseGet()` - Return value or supplier
```java
Optional<String> opt = Optional.ofNullable(null);
String name = opt.orElseGet(() → "Generated: " + System.currentTimeMillis());
```

### `orElseThrow()` - Throw exception if empty
```java
Optional<String> opt = Optional.ofNullable(null);
String name = opt.orElseThrow(() → new IllegalArgumentException("Name is required!"));
```

## Conditional Operations

### `ifPresent()` - Execute if value exists
```java
Optional<String> opt = Optional.of("Alice");

opt.ifPresent(name → System.out.println("Hello, " + name));
// Output: Hello, Alice

opt.ifPresentOrElse(
    name → System.out.println("Hello, " + name),
    () → System.out.println("Name is missing")
);
```

### `filter()` - Filter value
```java
Optional<String> opt = Optional.of("Alice");

Optional<String> filtered = opt.filter(name → name.length() > 3);
System.out.println(filtered.isPresent());  // true

Optional<String> filtered2 = opt.filter(name → name.length() > 10);
System.out.println(filtered2.isPresent());  // false
```

### `map()` - Transform value
```java
Optional<String> opt = Optional.of("Alice");

Optional<Integer> length = opt.map(String::length);
System.out.println(length.get());  // 5
```

### `flatMap()` - Transform Optional to Optional
```java
// If you return Optional from map, use flatMap
Optional<String> opt = Optional.of("Alice");

Optional<Integer> length = opt.flatMap(name → 
    Optional.of(name.length())
);
```

## Practical Examples

### Example 1: Avoiding NullPointerException
```java
public class OptionalDemo {
    public static void main(String[] args) {
        String name = getNameFromDatabase();  // Might return null
        
        // Without Optional (risky!)
        // System.out.println(name.length());  // Might crash
        
        // With Optional (safe!)
        Optional<String> nameOpt = Optional.ofNullable(name);
        nameOpt.ifPresent(n → System.out.println(n.length()));
        
        // Or with default
        String safeName = nameOpt.orElse("Unknown");
        System.out.println(safeName.length());  // Always safe
    }
    
    public static String getNameFromDatabase() {
        return null;  // Simulating null from DB
    }
}
```

### Example 2: Chaining Operations
```java
Optional<String> name = Optional.of("Alice");

int length = name
    .filter(n → n.length() > 2)
    .map(String::toUpperCase)
    .map(String::length)
    .orElse(0);

System.out.println(length);  // 5
```

### Example 3: User Authentication
```java
class User {
    String name;
    String email;
    
    User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

public class AuthDemo {
    public static void main(String[] args) {
        Optional<User> userOpt = findUserById(101);
        
        userOpt.ifPresentOrElse(
            user → {
                String email = user.email;
                if (email != null && email.contains("@")) {
                    System.out.println("Sending email to: " + email);
                }
            },
            () → System.out.println("User not found")
        );
    }
    
    public static Optional<User> findUserById(int id) {
        if (id == 101) {
            return Optional.of(new User("Alice", "alice@example.com"));
        }
        return Optional.empty();
    }
}
```

## When to Use Optional

### DO Use For
- Return types that might be absent
- Method parameters (rarely)
- Avoiding null checks

### DON'T Use For
- Fields (use null checks instead)
- Collections (use empty collection instead)
- Method parameters (confusing)

## Optional vs Null

```java
// Returning null
public String findName(int id) {
    return null;  // Unclear if error or no result
}

// Returning Optional
public Optional<String> findName(int id) {
    if (id == 1) {
        return Optional.of("Alice");
    }
    return Optional.empty();  // Clear: no result found
}
```

## Key Takeaways

1. **Optional<T>** container for value that may be absent
2. Use `ofNullable()` for values that might be null
3. **Never use `get()`** without checking
4. Use `orElse()` or `orElseGet()` for default values
5. Use `ifPresent()` for conditional operations
6. Use `map()` and `flatMap()` for transformations
7. Prefer Optional over null for return types

## Practice

1. Create Optional for user email, provide default if null
2. Chain filter and map operations
3. Use ifPresent to execute code conditionally
4. Create method returning Optional for student grade
5. Practice with orElse, orElseGet, orElseThrow
