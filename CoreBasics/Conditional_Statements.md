# Conditional Statements

## What are Conditionals?

Conditionals let your program make decisions. "IF this happens, THEN do that."

## 1. if Statement

The simplest conditional - execute code only if condition is true.

```java
if (condition) {
    // code to execute if condition is true
}
```

### Example
```java
int age = 20;

if (age >= 18) {
    System.out.println("You are an adult");
}
// Output: You are an adult
```

## 2. if-else Statement

Execute one block if true, another if false.

```java
if (condition) {
    // code if true
} else {
    // code if false
}
```

### Example
```java
int age = 16;

if (age >= 18) {
    System.out.println("You can vote");
} else {
    System.out.println("You cannot vote yet");
}
// Output: You cannot vote yet
```

## 3. if-else if-else Statement

Check multiple conditions in order.

```java
if (condition1) {
    // code if condition1 is true
} else if (condition2) {
    // code if condition2 is true
} else if (condition3) {
    // code if condition3 is true
} else {
    // code if all conditions are false
}
```

### Example: Grading System
```java
int marks = 85;

if (marks >= 90) {
    System.out.println("Grade: A+");
} else if (marks >= 80) {
    System.out.println("Grade: A");
} else if (marks >= 70) {
    System.out.println("Grade: B+");
} else if (marks >= 60) {
    System.out.println("Grade: B");
} else {
    System.out.println("Grade: F");
}
// Output: Grade: A
```

**Important:** Conditions are checked top to bottom. First true condition wins!

## 4. switch Statement

Clean way to check one variable against multiple values.

### Syntax
```java
switch (variable) {
    case value1:
        // code
        break;
    case value2:
        // code
        break;
    default:
        // code if no case matches
}
```

### Example: Days of Week
```java
int day = 3;

switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    case 2:
        System.out.println("Tuesday");
        break;
    case 3:
        System.out.println("Wednesday");
        break;
    case 4:
        System.out.println("Thursday");
        break;
    case 5:
        System.out.println("Friday");
        break;
    case 6:
        System.out.println("Saturday");
        break;
    case 7:
        System.out.println("Sunday");
        break;
    default:
        System.out.println("Invalid day");
}
// Output: Wednesday
```

### Why `break` is Important

Without `break`, execution "falls through" to next case:

```java
int day = 1;

switch (day) {
    case 1:
        System.out.println("Weekday");  // Executes
        // No break! Falls through
    case 6:
        System.out.println("Weekend");  // Also executes!
        break;
}
```

## switch with String (Java 7+)

```java
String day = "Monday";

switch (day) {
    case "Monday":
        System.out.println("Start of week");
        break;
    case "Friday":
        System.out.println("End of week");
        break;
    default:
        System.out.println("Midweek");
}
```

## Enhanced switch (Java 14+)

Newer, cleaner syntax:

```java
String day = "Monday";

String type = switch (day) {
    case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" → "Weekday";
    case "Saturday", "Sunday" → "Weekend";
    default → "Invalid";
};

System.out.println(type);  // Weekday
```

## Nested Conditionals

You can put if statements inside if statements:

```java
int age = 20;
boolean hasLicense = true;

if (age >= 18) {
    if (hasLicense) {
        System.out.println("Can drive alone");
    } else {
        System.out.println("Need to get license first");
    }
} else {
    System.out.println("Too young to drive");
}
```

**But be careful:** Deep nesting makes code hard to read!

## Conditional Operators

### Ternary Operator `? :`

Shorthand for simple if-else:

```java
// Traditional if-else
int age = 20;
String status;
if (age >= 18) {
    status = "Adult";
} else {
    status = "Minor";
}

// Ternary operator (one line!)
String status = (age >= 18) ? "Adult" : "Minor";
```

### Logical AND `&&`

Both conditions must be true:

```java
int age = 25;
boolean hasMoney = true;

if (age >= 18 && hasMoney) {
    System.out.println("Can buy ticket");
}
```

### Logical OR `||`

At least one condition must be true:

```java
int age = 16;
boolean hasPermission = true;

if (age >= 18 || hasPermission) {
    System.out.println("Can enter");
}
```

## Common Patterns

### Checking Ranges
```java
int score = 85;

if (score >= 0 && score <= 100) {
    System.out.println("Valid score");
}
```

### Multiple Conditions
```java
int day = 5;

if (day >= 1 && day <= 5) {
    System.out.println("Weekday");
} else if (day == 6 || day == 7) {
    System.out.println("Weekend");
}
```

### Checking Equality
```java
char grade = 'A';

if (grade == 'A') {
    System.out.println("Excellent!");
} else if (grade == 'B') {
    System.out.println("Good!");
}
```

## Common Mistakes

 **Using `=` instead of `==`**
```java
if (age = 18) {  // Wrong! Assignment, not comparison
}
```

 **Correct:**
```java
if (age == 18) {  // Comparison
}
```

 **Forgetting curly braces (dangerous!)**
```java
if (age >= 18)
    System.out.println("Adult");
    System.out.println("Can vote");  // This always runs!
```

 **Correct:**
```java
if (age >= 18) {
    System.out.println("Adult");
    System.out.println("Can vote");
}
```

## Best Practices

1. **Order matters:** Check most specific conditions first
2. **Use else if:** Don't create separate if statements for mutually exclusive conditions
3. **Consider switch:** Better for multiple equality checks on same variable
4. **Keep it simple:** Don't nest too deeply - consider refactoring
5. **Use meaningful conditions:** Make code readable

## Practical Example: Student Grading System

```java
import java.util.Scanner;

public class GradingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter marks (0-100): ");
        int marks = scanner.nextInt();

        char grade;
        if (marks >= 90) {
            grade = 'A';
        } else if (marks >= 80) {
            grade = 'B';
        } else if (marks >= 70) {
            grade = 'C';
        } else if (marks >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("Your grade: " + grade);

        // Pass/Fail check
        if (marks >= 60) {
            System.out.println("Status: PASSED ");
        } else {
            System.out.println("Status: FAILED ");
        }

        scanner.close();
    }
}
```

## Key Takeaways

1. `if` - execute if condition true
2. `if-else` - execute one of two blocks
3. `if-else if-else` - check multiple conditions
4. `switch` - cleaner for multiple value checks
5. Conditions are checked top to bottom, first wins
6. Use `break` in switch cases
7. Ternary operator `? :` for simple conditions

## Practice

Write a program that:
1. Takes three subject marks
2. Calculates average
3. Prints grade using if-else if-else
4. Prints "Pass" or "Fail"
