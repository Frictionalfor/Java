# Package Management

## What is a Package?

A **package** is like a folder that organizes related classes. Just like you organize files on your computer into folders.

```
com/
  | company/
  |   | employee/
  |   |   | Employee.java
  |   |   | Manager.java
  |   | department/
  |       | Department.java
  |       | HR.java
  | utils/
      | StringUtils.java
      | MathUtils.java
```

## Why Use Packages?

1. **Organization**: Group related classes together
2. **Avoid naming conflicts**: Two classes with same name in different packages
3. **Access control**: Use protected and package-private access
4. **Easy to search**: Find classes quickly

## Package Naming Convention

**Reverse domain name** (all lowercase):
```
com.companyname.projectname.module

Examples:
com.google.gson
org.springframework.boot
java.util
javax.swing
```

## Types of Packages

### 1. Built-in Packages
Provided by Java.

```java
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
```

Common built-in packages:
- `java.lang` - Automatically imported (String, System, Math)
- `java.util` - Utilities (Scanner, ArrayList, Date)
- `java.io` - Input/Output
- `java.net` - Networking

### 2. User-Defined Packages
Packages you create for your own classes.

```java
package com.student;

public class Student {
    // class code
}
```

## Creating a Package

### Step 1: Declare Package at Top of File
```java
package com.student;

public class Student {
    // class code
}
```

### Step 2: Save in Matching Folder Structure
```
project/
  | com/
      | student/
          | Student.java
```

## Importing Packages

### Import Specific Class
```java
import java.util.Scanner;  // Import just Scanner

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
```

### Import All Classes in Package
```java
import java.util.*;  // Import all from java.util

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList list = new ArrayList();
    }
}
```

### Without Import (Fully Qualified Name)
```java
public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
    }
}
```

**Tip:** Use imports - cleaner and easier to read!

## Access Across Packages

| Modifier | Same Class | Same Package | Subclass (diff pkg) | Other Packages |
|----------|-----------|--------------|---------------------|----------------|
| `private` |  |  |  |  |
| `default` |  |  |  |  |
| `protected` |  |  |  |  |
| `public` |  |  |  |  |

### Example: Access Modifiers Across Packages

**Package 1: com.student**
```java
package com.student;

public class Student {
    public String name;
    protected int rollNumber;
    int age;  // default
    private double marks;
    
    public Student(String name, int rollNumber, int age, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.age = age;
        this.marks = marks;
    }
}
```

**Package 2: com.school**
```java
package com.school;

import com.student.Student;

public class School {
    public static void main(String[] args) {
        Student s = new Student("Alice", 101, 20, 95.5);
        
        System.out.println(s.name);         //  public
        System.out.println(s.rollNumber);   //  protected (subclass)
        // System.out.println(s.age);       //  default (different package)
        // System.out.println(s.marks);     //  private
    }
}
```

## Static Import

Import static members so you don't need class name.

```java
import static java.lang.Math.*;  // Import all static from Math

public class Main {
    public static void main(String[] args) {
        double result = sqrt(16) + pow(2, 3);
        System.out.println(result);  // 4.0 + 8.0 = 12.0
    }
}
```

Without static import:
```java
double result = Math.sqrt(16) + Math.pow(2, 3);
```

## Complete Example: Package Structure

```
project/
  | com/
      | myapp/
          | Main.java
          | model/
          |   | User.java
          | service/
              | UserService.java
```

**User.java** (`com.myapp.model`)
```java
package com.myapp.model;

public class User {
    private String username;
    private String email;
    
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
}
```

**UserService.java** (`com.myapp.service`)
```java
package com.myapp.service;

import com.myapp.model.User;

public class UserService {
    public void printUserInfo(User user) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
    }
}
```

**Main.java** (`com.myapp`)
```java
package com.myapp;

import com.myapp.model.User;
import com.myapp.service.UserService;

public class Main {
    public static void main(String[] args) {
        User user = new User("alice", "alice@example.com");
        UserService service = new UserService();
        service.printUserInfo(user);
    }
}
```

## Compiling with Packages

### Using Command Line
```bash
# Compile from project root
javac com/myapp/model/User.java
javac com/myapp/service/UserService.java
javac com/myapp/Main.java

# Run
java com.myapp.Main
```

### Using IDE
Just click Run - IDE handles everything!

## Creating JAR File

Package your classes into a single file.

```bash
# Create JAR
jar cf myapp.jar com/myapp/*.class

# Run from JAR
java -cp myapp.jar com.myapp.Main
```

## Common Package Patterns

### 1. `com.company.project`
```
com.google.gmail
com.microsoft.office
org.apache.hadoop
```

### 2. Functional Packages
```
com.myapp.model      # Data classes
com.myapp.service     # Business logic
com.myapp.controller  # Request handling
com.myapp.util        # Helper classes
com.myapp.exception   # Custom exceptions
```

## Benefits of Packages

1. **Organized code**: Easy to find classes
2. **No naming conflicts**: Same class name in different packages
3. **Access protection**: Control visibility
4. **Reusability**: Easy to share packages
5. **Maintainability**: Easy to update and manage

## Key Takeaways

1. Package = folder for organizing classes
2. Use reverse domain name convention
3. `import` keyword brings classes into scope
4. Access modifiers control visibility across packages
5. `default` = package-private (no modifier)
6. Packages prevent naming conflicts

## Practice

1. Create package structure: `com.myapp.utils`
2. Create utility class with static methods
3. Import and use it from another package
4. Try accessing default and private members from different package
