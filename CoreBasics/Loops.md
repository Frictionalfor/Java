# Loops

## What are Loops?

Loops repeat code multiple times. Instead of writing the same code again and again, use a loop!

```
Without loop:  System.out.println("Hi");
                System.out.println("Hi");
                System.out.println("Hi");
                System.out.println("Hi");
                System.out.println("Hi");

With loop:     for (int i = 0; i < 5; i++) {
                  System.out.println("Hi");
              }
```

## 1. for Loop

Best when you know exactly how many times to repeat.

### Syntax
```java
for (initialization; condition; update) {
    // code to repeat
}
```

### Parts Explained
1. **Initialization**: Runs once at start (`int i = 0`)
2. **Condition**: Checked before each iteration (`i < 5`)
3. **Update**: Runs after each iteration (`i++`)

### Example: Print 1 to 5
```java
for (int i = 1; i <= 5; i++) {
    System.out.println("i = " + i);
}
// Output:
// i = 1
// i = 2
// i = 3
// i = 4
// i = 5
```

### Flow
```
1. i = 1 (initialization)
2. Is i <= 5? Yes → execute loop
3. Print i
4. i++ (i becomes 2)
5. Is i <= 5? Yes → execute loop
6. ... continues ...
7. Is i <= 5? No → exit loop
```

## 2. while Loop

Best when you don't know how many times to repeat (condition-based).

### Syntax
```java
while (condition) {
    // code to repeat
}
```

### Example: Countdown
```java
int count = 5;

while (count > 0) {
    System.out.println("Count: " + count);
    count--;
}
// Output:
// Count: 5
// Count: 4
// Count: 3
// Count: 2
// Count: 1
```

### Important
- Condition checked BEFORE each iteration
- If condition is false initially, loop never runs
- Make sure to update the condition variable, or you'll get infinite loop!

## 3. do-while Loop

Similar to while, but executes at least ONCE.

### Syntax
```java
do {
    // code to repeat
} while (condition);
```

### Example
```java
int i = 1;

do {
    System.out.println("i = " + i);
    i++;
} while (i <= 5);
// Output: 1, 2, 3, 4, 5
```

### Key Difference from while

```java
// while loop
int i = 6;
while (i <= 5) {
    System.out.println("This won't print");
}

// do-while loop
int i = 6;
do {
    System.out.println("This WILL print once");
} while (i <= 5);
// Output: This WILL print once
```

## 4. Enhanced for Loop (For-Each)

Simpler way to loop through arrays and collections.

### Syntax
```java
for (dataType variable : collection) {
    // code
}
```

### Example: Array
```java
int[] numbers = {1, 2, 3, 4, 5};

for (int num : numbers) {
    System.out.println(num);
}
// Output: 1, 2, 3, 4, 5
```

### How it Works
- `num` takes each value from `numbers` array
- Loops through all elements
- No index needed!

### Example: Strings
```java
String[] fruits = {"Apple", "Banana", "Orange"};

for (String fruit : fruits) {
    System.out.println("I like " + fruit);
}
// Output:
// I like Apple
// I like Banana
// I like Orange
```

## Loop Control Statements

### break - Exit Loop Early

```java
for (int i = 1; i <= 10; i++) {
    if (i == 5) {
        break;  // Exit loop when i is 5
    }
    System.out.println("i = " + i);
}
// Output: 1, 2, 3, 4
```

### continue - Skip Current Iteration

```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) {
        continue;  // Skip when i is 3
    }
    System.out.println("i = " + i);
}
// Output: 1, 2, 4, 5 (3 is skipped)
```

## Infinite Loops (Don't Do This!)

```java
//  BAD - Never ends!
while (true) {
    System.out.println("This runs forever");
}

//  BAD - Never ends!
for (int i = 0; ; i++) {
    System.out.println("This runs forever");
}
```

**Use case for infinite loops:**
```java
// Actually useful - server waiting for requests
while (true) {
    // Wait for user input
    // Process input
    // If user types "exit", break
    String input = getInput();
    if (input.equals("exit")) {
        break;
    }
}
```

## Nested Loops

Loop inside another loop.

### Example: Multiplication Table
```java
for (int i = 1; i <= 5; i++) {
    for (int j = 1; j <= 5; j++) {
        System.out.print(i * j + "\t");
    }
    System.out.println();
}
// Output:
// 1   2   3   4   5
// 2   4   6   8   10
// 3   6   9   12  15
// 4   8   12  16  20
// 5   10  15  20  25
```

### Example: Pattern
```java
for (int i = 1; i <= 5; i++) {
    for (int j = 1; j <= i; j++) {
        System.out.print("* ");
    }
    System.out.println();
}
// Output:
// *
// * *
// * * *
// * * * *
// * * * * *
```

## Choosing the Right Loop

| Loop | When to Use |
|------|-------------|
| `for` | Fixed number of iterations |
| `while` | Unknown iterations, condition-based |
| `do-while` | Must execute at least once |
| `enhanced for` | Iterating arrays/collections |

## Practical Examples

### Example 1: Sum of First n Numbers
```java
int n = 10;
int sum = 0;

for (int i = 1; i <= n; i++) {
    sum += i;
}

System.out.println("Sum of 1 to " + n + " = " + sum);
// Output: Sum of 1 to 10 = 55
```

### Example 2: Factorial
```java
int n = 5;
int factorial = 1;

for (int i = 1; i <= n; i++) {
    factorial *= i;
}

System.out.println("Factorial of " + n + " = " + factorial);
// Output: Factorial of 5 = 120
```

### Example 3: Find Even Numbers
```java
for (int i = 1; i <= 20; i++) {
    if (i % 2 == 0) {
        System.out.println(i + " is even");
    }
}
```

### Example 4: Calculator using Loop
```java
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            System.out.print("\nEnter first number: ");
            double a = scanner.nextDouble();

            System.out.print("Enter operator (+, -, *, /): ");
            char op = scanner.next().charAt(0);

            System.out.print("Enter second number: ");
            double b = scanner.nextDouble();

            double result = 0;
            boolean valid = true;

            switch (op) {
                case '+': result = a + b; break;
                case '-': result = a - b; break;
                case '*': result = a * b; break;
                case '/': result = a / b; break;
                default: valid = false;
            }

            if (valid) {
                System.out.println("Result: " + result);
            } else {
                System.out.println("Invalid operator!");
            }

            System.out.print("Do another? (y/n): ");
            choice = scanner.next().charAt(0);

        } while (choice == 'y' || choice == 'Y');

        scanner.close();
    }
}
```

## Common Mistakes

 **Off-by-one error**
```java
// Want to print 1 to 5, but...
for (int i = 1; i < 5; i++) {  // Wrong! Prints only 1-4
    System.out.println(i);
}
```

 **Correct:**
```java
for (int i = 1; i <= 5; i++) {  // Prints 1-5
    System.out.println(i);
}
```

 **Infinite loop (forgot to update)**
```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    // Forgot i++ - runs forever!
}
```

## Key Takeaways

1. `for` loop: Fixed iterations
2. `while` loop: Condition-based, check before
3. `do-while` loop: Executes at least once
4. Enhanced for: Easy array/collection iteration
5. `break` exits loop, `continue` skips iteration
6. Avoid infinite loops (always update condition variable)
7. Choose right loop for the task

## Practice

1. Print numbers 1 to 100
2. Print only even numbers 1 to 50
3. Calculate sum of digits of a number (e.g., 123 → 1+2+3 = 6)
4. Print multiplication table of 7
5. Find factorial of a number
