# Operators

## What are Operators?

Operators are symbols that perform operations on variables and values.

```
5 + 3 = 8
 ^   ^
operand operator operand
```

## 1. Arithmetic Operators

Used for mathematical operations.

| Operator | Name | Example | Result |
|----------|------|---------|--------|
| `+` | Addition | 5 + 3 | 8 |
| `-` | Subtraction | 5 - 3 | 2 |
| `*` | Multiplication | 5 * 3 | 15 |
| `/` | Division | 5 / 2 | 2 |
| `%` | Modulo (remainder) | 5 % 2 | 1 |

### Examples
```java
int a = 10;
int b = 3;

System.out.println(a + b);  // 13
System.out.println(a - b);  // 7
System.out.println(a * b);  // 30
System.out.println(a / b);  // 3 (integer division!)
System.out.println(a % b);  // 1 (remainder)
```

### Important: Integer Division
```java
int result = 5 / 2;
System.out.println(result);  // 2 (not 2.5!)
```

To get decimal result:
```java
double result = 5.0 / 2;  // 2.5
// or
double result = 5 / 2.0;  // 2.5
```

## 2. Relational (Comparison) Operators

Compare two values and return boolean.

| Operator | Meaning | Example | Result |
|----------|---------|---------|--------|
| `==` | Equal to | 5 == 5 | true |
| `!=` | Not equal to | 5 != 3 | true |
| `>` | Greater than | 5 > 3 | true |
| `<` | Less than | 5 < 3 | false |
| `>=` | Greater than or equal | 5 >= 5 | true |
| `<=` | Less than or equal | 5 <= 3 | false |

### Examples
```java
int age = 18;

System.out.println(age == 18);   // true
System.out.println(age != 18);   // false
System.out.println(age > 18);    // false
System.out.println(age < 18);    // false
System.out.println(age >= 18);   // true
System.out.println(age <= 18);   // true
```

## 3. Logical Operators

Work with boolean values.

| Operator | Meaning | Example | Result |
|----------|---------|---------|--------|
| `&&` | AND | true && false | false |
| `||` | OR | true || false | true |
| `!` | NOT | !true | false |

### AND (`&&`)
Both conditions must be true.

```java
int age = 25;
boolean hasLicense = true;

if (age >= 18 && hasLicense) {
    System.out.println("Can drive");
}
// Both must be true
```

### OR (`||`)
At least one condition must be true.

```java
int age = 16;
boolean hasLicense = false;

if (age >= 18 || hasLicense) {
    System.out.println("Can drive with adult");
}
```

### NOT (`!`)
Reverses the value.

```java
boolean is raining = true;
System.out.println(!isRaining);  // false
```

## 4. Assignment Operators

Assign values to variables.

| Operator | Example | Same As |
|----------|---------|---------|
| `=` | x = 5 | x = 5 |
| `+=` | x += 3 | x = x + 3 |
| `-=` | x -= 3 | x = x - 3 |
| `*=` | x *= 3 | x = x * 3 |
| `/=` | x /= 3 | x = x / 3 |
| `%=` | x %= 3 | x = x % 3 |

### Examples
```java
int x = 10;

x += 5;  // x = 15
x -= 3;  // x = 12
x *= 2;  // x = 24
x /= 4;  // x = 6
x %= 4;  // x = 2
```

## 5. Increment and Decrement Operators

Special operators for adding/subtracting 1.

### Post-increment (`x++`)
Use value first, then increment.

```java
int x = 5;
System.out.println(x++);  // Prints 5, then x becomes 6
System.out.println(x);    // Prints 6
```

### Pre-increment (`++x`)
Increment first, then use value.

```java
int x = 5;
System.out.println(++x);  // x becomes 6, prints 6
System.out.println(x);    // Prints 6
```

### Decrement operators (`x--`, `--x`)
Same logic but subtract 1.

```java
int x = 5;
System.out.println(x--);  // Prints 5, then x becomes 4
System.out.println(--x);  // x becomes 3, prints 3
```

## 6. Ternary Operator

Shorthand for if-else.

### Syntax
```java
condition ? valueIfTrue : valueIfFalse
```

### Example
```java
int age = 20;
String status = (age >= 18) ? "Adult" : "Minor";
System.out.println(status);  // Adult
```

**Same as:**
```java
String status;
if (age >= 18) {
    status = "Adult";
} else {
    status = "Minor";
}
```

## Operator Precedence

Which operator runs first?

### High to Low Priority
1. `()` - Parentheses
2. `++`, `--` - Increment/Decrement
3. `*`, `/`, `%` - Multiplication, Division, Modulo
4. `+`, `-` - Addition, Subtraction
5. `<`, `>`, `<=`, `>=` - Relational
6. `==`, `!=` - Equality
7. `&&` - Logical AND
8. `||` - Logical OR
9. `=`, `+=`, `-=` - Assignment

### Examples
```java
int result = 10 + 5 * 2;      // 20 (multiplication first)
int result2 = (10 + 5) * 2;   // 30 (parentheses first)
int result3 = 10 > 5 && 3 < 7; // true (both comparisons first)
```

**Tip:** Use parentheses to make code clear!

## Practical Example

```java
public class OperatorsDemo {
    public static void main(String[] args) {
        int a = 10;
        int b = 3;

        // Arithmetic
        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
        System.out.println("Modulo: " + (a % b));

        // Relational
        System.out.println("a > b: " + (a > b));
        System.out.println("a == b: " + (a == b));

        // Logical
        boolean x = true;
        boolean y = false;
        System.out.println("x && y: " + (x && y));
        System.out.println("x || y: " + (x || y));

        // Increment/Decrement
        int count = 5;
        System.out.println("count++: " + count++);  // 5
        System.out.println("++count: " + ++count);  // 7
    }
}
```

## Common Mistakes

 **Confusing `=` and `==`**
```java
if (age = 18) {  // Wrong! Assignment, not comparison
}
```

 **Correct:**
```java
if (age == 18) {  // Comparison
}
```

 **Wrong: Chaining comparisons**
```java
if (10 < age < 18) {  // Wrong! Java doesn't work this way
}
```

 **Correct:**
```java
if (age > 10 && age < 18) {  // Use logical operators
}
```

## Key Takeaways

1. Arithmetic: `+`, `-`, `*`, `/`, `%`
2. Relational: `==`, `!=`, `>`, `<`, `>=`, `<=`
3. Logical: `&&`, `||`, `!`
4. Assignment: `=`, `+=`, `-=`, etc.
5. Increment: `++`, Decrement: `--`
6. Ternary: `? :` for simple if-else
7. Use parentheses to control precedence

## Practice

Write a program that:
1. Takes two numbers
2. Performs all arithmetic operations
3. Compares them (which is larger?)
4. Uses logical operators to check if both are positive
