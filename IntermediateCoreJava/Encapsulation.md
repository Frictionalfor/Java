# Encapsulation

## What is Encapsulation?

Encapsulation = **Wrapping data (variables) and methods together** as a single unit, while **hiding internal details** from outside access.

Think of it like a **capsule pill**:
- Medicine inside = Data and methods
- Coating = Protection from outside
- You get the benefit without knowing the internals

## Why Encapsulation?

1. **Security**: Protect sensitive data
2. **Control**: Validate data before setting
3. **Flexibility**: Change internal implementation without affecting users
4. **Maintainability**: Easy to modify and debug

## How to Achieve Encapsulation

### Step 1: Make Variables Private
```java
public class Student {
    private String name;
    private int age;
    private double marks;
}
```

### Step 2: Provide Public Getters
```java
public String getName() {
    return name;
}

public int getAge() {
    return age;
}

public double getMarks() {
    return marks;
}
```

### Step 3: Provide Public Setters with Validation
```java
public void setName(String name) {
    this.name = name;
}

public void setAge(int age) {
    if (age > 0 && age < 150) {
        this.age = age;
    } else {
        System.out.println("Invalid age!");
    }
}

public void setMarks(double marks) {
    if (marks >= 0 && marks <= 100) {
        this.marks = marks;
    } else {
        System.out.println("Invalid marks!");
    }
}
```

## Complete Encapsulated Class

```java
public class Student {
    // Private variables (hidden from outside)
    private String name;
    private int rollNumber;
    private double marks;
    
    // Constructor
    public Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
    
    // Getters (read access)
    public String getName() {
        return name;
    }
    
    public int getRollNumber() {
        return rollNumber;
    }
    
    public double getMarks() {
        return marks;
    }
    
    // Setters (write access with validation)
    public void setName(String name) {
        this.name = name;
    }
    
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    public void setMarks(double marks) {
        if (marks >= 0 && marks <= 100) {
            this.marks = marks;
        } else {
            System.out.println("Invalid marks! Must be 0-100");
        }
    }
    
    // Public methods (controlled behavior)
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll: " + rollNumber);
        System.out.println("Marks: " + marks);
    }
    
    public String getGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B+";
        return "B";
    }
}

// Usage
Student s1 = new Student("Alice", 101, 95.5);

// Read using getters
System.out.println(s1.getName());      // Alice
System.out.println(s1.getMarks());     // 95.5

// Write using setters
s1.setMarks(98.0);  // OK
s1.setMarks(150);   // Error message shown

// Cannot access directly
// s1.marks = 150;  // Error! Private
```

## Getters and Setters Explained

### Getters (Accessors)
- Read value of private variable
- Usually named `getVariableName()`
- Return type matches variable type

```java
public String getName() {
    return name;
}
```

### Setters (Mutators)
- Set value of private variable
- Usually named `setVariableName()`
- Return type is `void`
- Include validation

```java
public void setAge(int age) {
    if (age > 0) {
        this.age = age;
    }
}
```

## Validation Example

```java
public class BankAccount {
    private double balance;
    private String accountHolder;
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount!");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
    
    public double getBalance() {
        return balance;
    }
}

// Usage
BankAccount acc = new BankAccount();
acc.deposit(1000);    // OK
acc.deposit(-500);    // Error message
acc.withdraw(2000);   // Error message
System.out.println(acc.getBalance());  // 1000
```

## Benefits of Encapsulation

### Before Encapsulation (Bad)
```java
public class BadStudent {
    public int marks;  // Anyone can change!
}

BadStudent s = new BadStudent();
s.marks = -50;  // Invalid! But no one stopped it
s.marks = 200;  // Invalid! But no one stopped it
```

### After Encapsulation (Good)
```java
public class GoodStudent {
    private int marks;
    
    public void setMarks(int marks) {
        if (marks >= 0 && marks <= 100) {
            this.marks = marks;
        } else {
            System.out.println("Invalid marks!");
        }
    }
}

GoodStudent s = new GoodStudent();
s.setMarks(-50);   // Error message shown
s.setMarks(200);   // Error message shown
s.setMarks(85);    // OK
```

## Encapsulation vs Abstraction

| Encapsulation | Abstraction |
|---------------|-------------|
| Hides data using access modifiers | Hides implementation details |
| Data hiding | Complexity hiding |
| Achieved with private + getters/setters | Achieved with abstract classes/interfaces |
| Focus: Data security | Focus: Complexity reduction |

## Real-World Analogy

**ATM Machine:**
- **Encapsulation**: Your bank account balance is private. You can only access through ATM (getters/setters).
- **Abstraction**: You don't need to know how ATM works internally. Just insert card, enter PIN, withdraw money.

## Practical Example: Employee Class

```java
public class Employee {
    private String name;
    private double salary;
    private int employeeId;
    
    public Employee(String name, int employeeId, double salary) {
        this.name = name;
        this.employeeId = employeeId;
        setSalary(salary);  // Use setter for validation
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }
    
    public double getSalary() {
        return salary;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Salary cannot be negative!");
        }
    }
    
    // Business methods
    public void giveRaise(double percentage) {
        if (percentage > 0) {
            salary += salary * percentage / 100;
            System.out.println("Raise given! New salary: " + salary);
        }
    }
    
    public void displayDetails() {
        System.out.println("ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}

// Usage
Employee emp = new Employee("Alice", 101, 50000);
emp.displayDetails();
emp.giveRaise(10);
System.out.println("Updated salary: " + emp.getSalary());
// emp.salary = -1000;  // Error! Private
```

## Key Takeaways

1. **Encapsulation** = Data hiding + Controlled access
2. Make variables `private`
3. Provide `public` getters to read
4. Provide `public` setters with validation to write
5. Protects data from invalid values
6. Makes code maintainable and flexible

## Practice

1. Create `Product` class with private price and quantity
2. Add validation (price > 0, quantity >= 0)
3. Add method to calculate total value
4. Try setting invalid values and see validation work
