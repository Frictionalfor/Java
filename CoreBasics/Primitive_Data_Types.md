# Primitive Data Types

## What are Data Types?

Data types specify what kind of data a variable can hold. Like choosing the right container for your stuff:
- Use a cup for liquids
- Use a box for solid items
- Use a fridge for perishables

## The 8 Primitive Data Types

### 1. `byte` - The Smallest Integer
- **Size:** 1 byte (8 bits)
- **Range:** -128 to 127
- **Use:** Saving memory in large arrays

```java
byte smallNumber = 100;
// Perfect for small numbers
```

### 2. `short` - Small Integer
- **Size:** 2 bytes (16 bits)
- **Range:** -32,768 to 32,767
- **Use:** Rarely used, mostly for legacy systems

```java
short age = 25;
```

### 3. `int` - Standard Integer (Most Common!)
- **Size:** 4 bytes (32 bits)
- **Range:** -2,147,483,648 to 2,147,483,647
- **Use:** Default choice for whole numbers

```java
int students = 500;
int year = 2024;
```

**When to use:** Most whole numbers in your program

### 4. `long` - Large Integer
- **Size:** 8 bytes (64 bits)
- **Range:** Huge! -9 quintillion to 9 quintillion
- **Use:** Very large numbers

```java
long worldPopulation = 7800000000L;  // Note the 'L' at end
```

**Important:** Add `L` or `l` at the end to tell Java it's a long

### 5. `float` - Decimal Numbers (Single Precision)
- **Size:** 4 bytes (32 bits)
- **Precision:** 6-7 decimal digits
- **Use:** Saving memory in large arrays of decimals

```java
float price = 19.99F;  // Note the 'F' at end
```

**Important:** Add `F` or `f` at the end

### 6. `double` - Decimal Numbers (Double Precision) 
- **Size:** 8 bytes (64 bits)
- **Precision:** 15 decimal digits
- **Use:** Default choice for decimal numbers

```java
double salary = 50000.50;
double pi = 3.14159265359;
```

**When to use:** Most decimal numbers (this is the default!)

### 7. `char` - Single Character
- **Size:** 2 bytes (16 bits)
- **Range:** 0 to 65,535 (Unicode)
- **Use:** Single letters, digits, symbols

```java
char grade = 'A';
char symbol = '$';
char digit = '7';
```

**Important:** Use single quotes `''` for chars, double quotes `""` for strings

### 8. `boolean` - True or False
- **Size:** 1 bit (actually 1 byte in Java)
- **Values:** `true` or `false` only
- **Use:** Conditions, flags

```java
boolean isStudent = true;
boolean hasPassed = false;
```

## Size Comparison

```
byte (1B) < short (2B) < int (4B) < long (8B)
float (4B) < double (8B)
char (2B)
boolean (1B)
```

## Default Values

If you don't assign a value:

| Type | Default Value |
|------|---------------|
| byte | 0 |
| short | 0 |
| int | 0 |
| long | 0L |
| float | 0.0f |
| double | 0.0d |
| char | '\u0000' (empty char) |
| boolean | false |

## Choosing the Right Type

### Decision Tree

**Need whole numbers?**
- Small range (-128 to 127)? → `byte`
- Medium range? → `short`
- Normal range? → `int` 
- Very large numbers? → `long`

**Need decimals?**
- General use? → `double` 
- Memory critical? → `float`

**Need true/false?**
→ `boolean`

**Need single character?**
→ `char`

## Practical Examples

```java
public class DataTypesExample {
    public static void main(String[] args) {
        // Whole numbers
        byte studentsInClass = 45;
        short schoolCode = 1234;
        int totalStudents = 5000;
        long worldPopulation = 7800000000L;

        // Decimal numbers
        float temperature = 98.6F;
        double preciseValue = 3.14159265359;

        // Characters and booleans
        char grade = 'A';
        boolean isPassed = true;

        // Print them all
        System.out.println("Students in class: " + studentsInClass);
        System.out.println("School code: " + schoolCode);
        System.out.println("Total students: " + totalStudents);
        System.out.println("World population: " + worldPopulation);
        System.out.println("Temperature: " + temperature);
        System.out.println("Pi value: " + preciseValue);
        System.out.println("Grade: " + grade);
        System.out.println("Passed: " + isPassed);
    }
}
```

## Type Casting

### Widening Casting (Automatic)
Smaller → Larger (no data loss)

```java
int myInt = 100;
double myDouble = myInt;  // Automatic: int to double
System.out.println(myDouble);  // 100.0
```

### Narrowing Casting (Manual)
Larger → Smaller (possible data loss)

```java
double myDouble = 9.78;
int myInt = (int) myDouble;  // Manual: double to int
System.out.println(myInt);  // 9 (decimal part lost)
```

## Common Mistakes

 **Wrong:** Forgetting `F` for float
```java
float price = 19.99;  // Error! Java thinks it's double
```

 **Correct:**
```java
float price = 19.99F;
```

 **Wrong:** Using double quotes for char
```java
char letter = "A";  // Error! This is a string
```

 **Correct:**
```java
char letter = 'A';
```

## Memory Matters!

Choosing the right data type saves memory:

```java
// Bad: Using long for age
long age = 25;  // Uses 8 bytes

// Good: Using int for age
int age = 25;   // Uses 4 bytes
```

In large programs, this adds up!

## Key Takeaways

1. There are 8 primitive data types
2. `int` for whole numbers, `double` for decimals (default choices)
3. Always add `L` for long, `F` for float
4. Use single quotes for char, double quotes for String
5. Choose the smallest type that fits your needs
6. Java automatically converts smaller to larger types

## Practice

Create variables for:
- Your age (int)
- Your height in meters (double)
- Your first initial (char)
- Whether you're a student (boolean)
- Number of siblings (byte)

Print them all!
