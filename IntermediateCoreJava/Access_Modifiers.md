# Access Modifiers

## What are Access Modifiers?

Access modifiers control **who can see and use** your class members (variables and methods). Like different levels of security clearance.

```
Public: Anyone can access
Protected: Family and subclasses
Default: Package members only
Private: Only the class itself
```

## The Four Access Modifiers

### 1. `private` - Most Restricted

**Accessible only within the same class.**

```java
public class BankAccount {
    private double balance;  // Private
    
    public void deposit(double amount) {
        balance += amount;  // OK - inside same class
    }
}

BankAccount account = new BankAccount();
// account.balance = 1000;  // Error! Private!
// System.out.println(account.balance);  // Error!
```

**Use for:** Sensitive data that shouldn't be directly accessed

### 2. `default` (No Modifier) - Package-Private

**Accessible only within the same package.**

```java
// File: com/bank/Account.java
class Account {
    int accountNumber;  // Default (package-private)
}

// File: com/bank/Main.java
Account acc = new Account();
acc.accountNumber = 123;  // OK - same package

// File: com/other/OtherClass.java
Account acc = new Account();
acc.accountNumber = 123;  // Error! Different package
```

**Use for:** Related classes in same package

### 3. `protected` - Family Access

**Accessible within package + subclasses (even in different packages).**

```java
// File: com/bank/Account.java
public class Account {
    protected double balance;  // Protected
}

// File: com/bank/Main.java (same package)
Account acc = new Account();
acc.balance = 1000;  // OK

// File: com/other/SavingsAccount.java (different package, but subclass)
public class SavingsAccount extends Account {
    public void addInterest() {
        balance += 10;  // OK - subclass
    }
}
```

**Use for:** Fields that should be inherited but not publicly exposed

### 4. `public` - Least Restricted

**Accessible from anywhere.**

```java
public class Student {
    public String name;  // Public
    
    public void study() {  // Public
        System.out.println("Studying...");
    }
}

// Anywhere can access
Student s = new Student();
s.name = "Alice";  // OK
s.study();  // OK
```

**Use for:** API methods that should be publicly available

## Comparison Table

| Modifier | Same Class | Same Package | Subclass | Other Packages |
|----------|-----------|--------------|----------|----------------|
| `private` |  |  |  |  |
| `default` |  |  |  |  |
| `protected` |  |  |  |  |
| `public` |  |  |  |  |

## Practical Example: Bank Account

### Bad Example (No Encapsulation)
```java
public class BadBankAccount {
    public double balance;  // Public - anyone can change!
}

BadBankAccount acc = new BadBankAccount();
acc.balance = -1000;  // Oh no! Negative balance allowed!
```

### Good Example (With Access Modifiers)
```java
public class BankAccount {
    private double balance;  // Private - cannot be changed directly
    
    // Public method to safely deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount!");
        }
    }
    
    // Public method to safely withdraw
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
    
    // Public method to check balance (read-only)
    public double getBalance() {
        return balance;
    }
}

// Usage
BankAccount acc = new BankAccount();
acc.deposit(1000);      // OK
acc.withdraw(500);      // OK
// acc.balance = -1000; // Error! Private!
System.out.println(acc.getBalance());  // 500
```

## Best Practices

### 1. Make Fields Private
```java
public class Student {
    private String name;
    private int age;
    // Private fields with public getters/setters
}
```

### 2. Provide Public Getters/Setters
```java
public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}
```

### 3. Validate in Setters
```java
public void setAge(int age) {
    if (age > 0 && age < 150) {  // Validation
        this.age = age;
    } else {
        System.out.println("Invalid age!");
    }
}
```

## Real-World Example: Student Class

```java
public class Student {
    private String name;
    private int rollNumber;
    private double marks;
    
    // Constructor
    public Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getRollNumber() {
        return rollNumber;
    }
    
    public double getMarks() {
        return marks;
    }
    
    // Setters with validation
    public void setName(String name) {
        this.name = name;
    }
    
    public void setMarks(double marks) {
        if (marks >= 0 && marks <= 100) {
            this.marks = marks;
        } else {
            System.out.println("Invalid marks!");
        }
    }
    
    // Public method
    public void displayGrade() {
        if (marks >= 90) {
            System.out.println("Grade: A+");
        } else if (marks >= 80) {
            System.out.println("Grade: A");
        } else {
            System.out.println("Grade: B");
        }
    }
}

// Usage
Student s1 = new Student("Alice", 101, 95.5);
s1.displayGrade();           // OK
// s1.marks = -10;           // Error! Private
s1.setMarks(98.0);           // OK - validation happens
```

## Access Modifiers Summary

| Modifier | Visibility | Use Case |
|----------|-----------|----------|
| `private` | Only class | Sensitive data, internal use |
| `default` | Package | Related classes in same package |
| `protected` | Package + subclasses | Inherited fields |
| `public` | Everywhere | Public API, external access |

## Key Takeaways

1. `private`: Most secure, use for sensitive data
2. `default`: Package-level access (no keyword)
3. `protected`: For inheritance scenarios
4. `public`: Least secure, use for APIs
5. Always make fields private
6. Use getters/setters for controlled access
7. Validate data in setters

## Practice

1. Create `Employee` class with private salary field
2. Add validation in setter (salary cannot be negative)
3. Add getter to return formatted salary with $ sign
4. Create test class to demonstrate access restrictions
