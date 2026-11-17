# Intermediate Core Java Notes

## Arrays

- **Single-dimensional**: Stores elements of the same type in contiguous memory
- **Multi-dimensional**: Arrays of arrays, useful for matrices

```java
int[] arr = new int[5];
int[][] matrix = new int[3][3];
```

## String Handling

- **String**: Immutable, stored in String Pool
- **StringBuilder**: Mutable, not thread-safe, faster
- **StringBuffer**: Mutable, thread-safe (synchronized)

## Methods

- Define reusable code blocks
- Can have parameters and return types
- Overloading: Same method name, different parameters

## Classes and Objects

- **Class**: Blueprint/template
- **Object**: Instance of a class
- **State**: Variables/fields
- **Behavior**: Methods

## Access Modifiers

| Modifier | Same Class | Same Package | Subclass | Other |
|----------|-----------|--------------|----------|-------|
| private | Yes | No | No | No |
| default | Yes | Yes | No | No |
| protected | Yes | Yes | Yes | No |
| public | Yes | Yes | Yes | Yes |

## Keywords

- **this**: Reference to current object
- **super**: Reference to parent class object
- **static**: Belongs to class, not instance
- **final**: Cannot be changed

## Encapsulation

- Wrapping data and methods together
- Achieved using private fields + public getters/setters

## Inheritance

- Child class acquires parent class properties
- `extends` keyword used
- Method overriding allows redefining parent methods

## Polymorphism

- **Compile-time**: Method overloading
- **Runtime**: Method overriding

## Abstraction

- Hiding implementation details
- **Abstract class**: Can have both abstract and concrete methods
- **Interface**: All methods are abstract (before Java 8)

## Package Management

- Organizes classes into namespaces
- `import` statement to use classes from other packages

## Exception Handling

- **Checked**: Compile-time checking (IOException)
- **Unchecked**: Runtime exceptions (NullPointerException)
- Keywords: `try`, `catch`, `finally`, `throw`, `throws`
